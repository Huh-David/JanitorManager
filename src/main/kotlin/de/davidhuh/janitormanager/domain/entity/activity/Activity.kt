package de.davidhuh.janitormanager.domain.entity.activity

import de.davidhuh.janitormanager.domain.entity.activityassignment.ActivityAssignment
import de.davidhuh.janitormanager.domain.entity.todo.Todo
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Activity(
	val startDate: LocalDate,
	val intervalInDays: Int, //DatePeriod
	val activityType: ActivityType,
	val activityAssignmentList: MutableList<ActivityAssignment> = mutableListOf(),
) {
	private fun createTodoList(): MutableList<Todo> {
		val today = LocalDate(
			Calendar.getInstance().get(Calendar.YEAR),
			Calendar.getInstance().get(Calendar.MONTH + 1),
			Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
		)
		var todoDay = this.startDate
		val todoList = mutableListOf<Todo>()
		var condition = todoDay < today

		while (condition) {
			condition = todoDay < today
			val todo = Todo(this, todoDay)
			todoList.add(todo)
			todoDay = todoDay.plus(DatePeriod(days = this.intervalInDays))
		}

		return todoList
	}

	fun addActivityAssignment(assignment: ActivityAssignment) {
		this.activityAssignmentList.add(assignment)
	}

	override fun toString(): String {
		return "$activityType - $startDate - $intervalInDays"
	}

	val todoList = createTodoList()
}
