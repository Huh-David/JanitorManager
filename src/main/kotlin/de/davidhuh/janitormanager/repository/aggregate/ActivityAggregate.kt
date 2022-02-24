package de.davidhuh.janitormanager.repository.aggregate

import de.davidhuh.janitormanager.domain.entity.Activity
import de.davidhuh.janitormanager.domain.entity.CleaningObject
import de.davidhuh.janitormanager.domain.entity.Todo
import de.davidhuh.janitormanager.domain.valueobjects.ActivityType
import de.davidhuh.janitormanager.repository.TodoRepo
import kotlinx.serialization.Serializable
import de.davidhuh.janitormanager.service.TodoService

@Serializable
class ActivityAggregate(
	var activityType: ActivityType,
	var activityList: MutableList<Activity>,
) {
	fun checkCompatibility(activity: Activity): Boolean {
		return activity.activityType == activityType
	}

	fun checkActivityExistence(activity: Activity): Boolean {
		return !activityList.none {
			it.startDate == activity.startDate && it.intervalInDays == activity.intervalInDays
		}
	}

	fun addActivity(activity: Activity): Boolean {
		if (checkCompatibility(activity)) {
			if (!checkActivityExistence(activity)) {
				activityList.add(activity)
				return true
			}
		}
		return false
	}

	fun getAllTodos(cleaningObject: CleaningObject): MutableList<Todo> {
		val todoList = mutableListOf<Todo>()

		for (activity in activityList) {
			val todoRepo = TodoRepo(activity)
			todoList.addAll(todoRepo.getTodos())
		}

		val todoService = TodoService(cleaningObject.address)
		val savedTodoList = todoService.readTodoList(activityType)

		for (savedTodo in savedTodoList) {
			if (!todoList.contains(savedTodo)) {
				todoList.add(savedTodo)
			} else if (todoList.contains(savedTodo)) {
				val todo = todoList.find { it == savedTodo }
				if (todo != null) {
					todo.doneDate = savedTodo.doneDate
				}
			}
		}

		todoList.sortBy { it.date }

		// only one to-do which is not overdue and not done should be present
		val firstNotOverdueTodo = todoList.first { !it.isOverdue() && !it.isDone() }
		todoList.removeAll { !it.isOverdue() && !it.isDone() }
		todoList.add(firstNotOverdueTodo)


		todoService.saveTodoList(todoList)

		return todoList
	}

	fun getFirstTodo(todoList: MutableList<Todo>): Todo {
		val tempTodo = todoList.first() { !it.isOverdue() }
		todoList.removeAll { !it.isOverdue() }
		todoList.add(tempTodo)

		return if (todoList.none() { !it.isDone() }) {
			todoList.last()
		} else {
			todoList.first() { !it.isDone() }
		}
	}
}
