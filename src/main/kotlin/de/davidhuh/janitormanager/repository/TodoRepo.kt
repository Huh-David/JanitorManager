package de.davidhuh.janitormanager.repository

import de.davidhuh.janitormanager.domain.Activity
import de.davidhuh.janitormanager.domain.Todo
import java.time.LocalDate

fun createTodoList(activity: Activity): MutableList<Todo> {
	val today = LocalDate.now()
	var todoDay = activity.startDate
	val todoList = mutableListOf<Todo>()

	while (todoDay < today) {
		val todo = Todo(activity, todoDay, overdue = true)
		todoList.add(todo)
		todoDay = todoDay.plusDays(activity.intervalInDays.toLong())
	}

	todoList.last().overdue = false

	return todoList
}

class TodoRepo(var activity: Activity) {
	val todoList = createTodoList(activity)
}
