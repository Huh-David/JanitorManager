package de.davidhuh.janitormanager.domain

import de.davidhuh.janitormanager.repository.createTodoList
import java.time.LocalDate


data class Activity(
	val startDate: LocalDate,
	val intervalInDays: Int,
	val activityType: ActivityType,
) {
	override fun toString(): String {
		return "$activityType - $startDate - $intervalInDays"
	}

	val todoList = createTodoList(this)
}
