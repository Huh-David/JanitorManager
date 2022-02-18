package de.davidhuh.janitormanager.domain

import de.davidhuh.janitormanager.repository.ActivityRepo
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable


@Serializable
data class ActivityRepoAssignment(
	val employeeList: Employee,
	val activityRepo: ActivityRepo,
	val startDate: LocalDate, // range of date when the employee is assigned to the activity
	val endDate: LocalDate,
) {

}
