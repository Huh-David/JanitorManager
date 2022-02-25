package de.davidhuh.janitormanager.usecase

import de.davidhuh.janitormanager.domain.entity.Activity
import de.davidhuh.janitormanager.domain.entity.Todo
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import java.util.*

object TodoGenerator {
//	fun createTodoList(activity: Activity) {
//		val today = LocalDate(
//			Calendar.getInstance().get(Calendar.YEAR),
//			Calendar.getInstance().get(Calendar.MONTH + 1),
//			Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
//		)
//		var todoDay = activity.startDate
//		val todoList = mutableListOf<Todo>()
//		var condition = todoDay < today
//
//		while (condition) {
//			condition = todoDay < today
//			val todo = Todo(activity, todoDay)
//			todoList.add(todo)
//			todoDay = todoDay.plus(DatePeriod(days = activity.intervalInDays))
//		}
//
//		activity.todoList = todoList
//	}
}
