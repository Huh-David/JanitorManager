package de.davidhuh.janitormanager.repository

import de.davidhuh.janitormanager.domain.Activity
import de.davidhuh.janitormanager.domain.Todo
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import java.util.Calendar

fun createTodoList(activity: Activity): MutableList<Todo> {
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

class TodoRepo(var activity: Activity) {
	val todoList = createTodoList(activity)
}
