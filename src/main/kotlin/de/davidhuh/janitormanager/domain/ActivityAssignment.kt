package de.davidhuh.janitormanager.domain

import java.time.LocalDate

data class ActivityAssignment(
	val employee: Employee,
	val activity: Activity,
	val startDate: LocalDate, // range of date when the employee is assigned to the activity
	val endDate: LocalDate,
) {

}
