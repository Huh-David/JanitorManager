package de.davidhuh.janitormanager.application

import de.davidhuh.janitormanager.domain.entity.Activity
import de.davidhuh.janitormanager.domain.entity.Todo


class TodoService(var activity: Activity) {
	fun getTodos(): MutableList<Todo> {
		val todoList = activity.todoList
		todoList.sortBy { it.date }
		return todoList
	}

	fun getFirstTodo(todoList: MutableList<Todo>): Todo {
		return if (todoList.none() { !it.isDone() }) {
			todoList.last()
		} else {
			todoList.first() { !it.isDone() }
		}
	}
}
