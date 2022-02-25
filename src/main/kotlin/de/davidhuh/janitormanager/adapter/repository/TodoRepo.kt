package de.davidhuh.janitormanager.adapter.repository

import de.davidhuh.janitormanager.domain.entity.Activity
import de.davidhuh.janitormanager.domain.entity.Todo
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import java.util.Calendar


class TodoRepo(var activity: Activity) {
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
