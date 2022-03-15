package de.davidhuh.janitormanager.application

import de.davidhuh.janitormanager.adapter.TodoRepo
import de.davidhuh.janitormanager.domain.entity.CleaningObject
import de.davidhuh.janitormanager.domain.entity.Todo
import de.davidhuh.janitormanager.domain.entity.aggregate.ActivityAggregate

class ActivityAggregateService(
	val activityAggregate: ActivityAggregate,
	val cleaningObject: CleaningObject,
) {
	fun getAllTodos(): MutableList<Todo> {
		val todoList = mutableListOf<Todo>()

		for (activity in activityAggregate.activityList) {
			val todoService = TodoService(activity)
			todoList.addAll(todoService.getTodos())
		}

		val todoRepo = TodoRepo(cleaningObject.address)
		val savedTodoList = todoRepo.readTodoList(activityAggregate.activityType)

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


		todoRepo.saveTodoList(todoList)

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
