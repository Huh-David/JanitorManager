package de.davidhuh.janitormanager.domain

import de.davidhuh.janitormanager.repository.TodoRepo
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Activity(
	val startDate: LocalDate,
	val intervalInDays: Int, //DatePeriod
	val activityType: ActivityType,
) {
	override fun toString(): String {
		return "$activityType - $startDate - $intervalInDays"
	}

	val todoList = TodoRepo(this).createTodoList()
}
