package de.davidhuh.janitormanager.domain

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import de.davidhuh.janitormanager.repository.createTodoList

@Serializable
data class Activity(
	val startDate: LocalDate,
	val intervalInDays: Int, //DatePeriod
	val activityType: ActivityType,
) {
	override fun toString(): String {
		return "$activityType - $startDate - $intervalInDays"
	}

	val todoList = createTodoList(this)
}
