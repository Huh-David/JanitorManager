package de.davidhuh.janitormanager.repository

import de.davidhuh.janitormanager.domain.Activity
import de.davidhuh.janitormanager.domain.CleaningObject
import de.davidhuh.janitormanager.domain.Todo
import de.davidhuh.janitormanager.service.TodoService
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import java.util.Calendar


class TodoRepo(var activity: Activity) {
	fun createTodoList(): MutableList<Todo> {
		val today = LocalDate(
			Calendar.getInstance().get(Calendar.YEAR),
			Calendar.getInstance().get(Calendar.MONTH + 1),
			Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
		)
		var todoDay = activity.startDate
		val todoList = mutableListOf<Todo>()
		var condition = todoDay < today

		while (condition) {
			condition = todoDay < today
			val todo = Todo(activity, todoDay)
			todoList.add(todo)
			todoDay = todoDay.plus(DatePeriod(days = activity.intervalInDays))
		}

		return todoList
	}

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
