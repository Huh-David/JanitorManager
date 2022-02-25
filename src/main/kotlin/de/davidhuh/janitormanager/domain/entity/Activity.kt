package de.davidhuh.janitormanager.domain.entity

import de.davidhuh.janitormanager.domain.valueobject.ActivityType
import de.davidhuh.janitormanager.adapter.repository.TodoRepo
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Activity(
	val startDate: LocalDate,
	val intervalInDays: Int, //DatePeriod
	val activityType: ActivityType,
	val activityAssignmentList: MutableList<ActivityAssignment> = mutableListOf(),
) {
	fun addActivityAssignment(assignment: ActivityAssignment) {
		this.activityAssignmentList.add(assignment)
	}

	override fun toString(): String {
		return "$activityType - $startDate - $intervalInDays"
	}

	val todoList = TodoRepo(this).createTodoList()
}
