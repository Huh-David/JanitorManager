package de.davidhuh.janitormanager.repository

import kotlinx.serialization.Serializable
import de.davidhuh.janitormanager.domain.Activity
import de.davidhuh.janitormanager.domain.ActivityType
import de.davidhuh.janitormanager.domain.CleaningObject
import de.davidhuh.janitormanager.domain.Todo
import de.davidhuh.janitormanager.service.TodoService

@Serializable
class ActivityRepo(
	var activityType: ActivityType,
	var activityList: MutableList<Activity>,
) {
	private fun checkCompatibility(activity: Activity): Boolean {
		return activity.activityType == activityType
	}

	private fun checkActivityExistence(activity: Activity): Boolean {
		return !activityList.none { it.startDate == activity.startDate && it.intervalInDays == activity.intervalInDays }
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
			for (todo in activity.todoList) {
				todoList.add(todo)
			}
		}

		val todoService = TodoService(cleaningObject)
		val savedTodoList = todoService.readTodoList(activityType)

		for (savedTodo in savedTodoList) {
			if (!todoList.contains(savedTodo)) {
				todoList.add(savedTodo)
			} else if (todoList.contains(savedTodo)) {
				val todo = todoList.find { it == savedTodo }
				if (todo != null) {
					todo.done = savedTodo.done
				}
			}
		}

		todoList.sortBy { it.date }
		todoService.saveTodoList(todoList)

		return todoList
	}

	fun getFirstTodo(todoList: List<Todo>): Todo {
		return if (todoList.none() { !it.done }) {
			todoList.last()
		} else {
			todoList.first() { !it.done }
		}
	}
}
