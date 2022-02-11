package de.davidhuh.janitormanager.domain

import java.time.LocalDate


data class Activity(
	val startDate: LocalDate,
	val intervalInDays: Int,
	val activityType: ActivityType,
) {

}
