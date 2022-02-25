package de.davidhuh.janitormanager.domain

import de.davidhuh.janitormanager.domain.entity.Activity
import de.davidhuh.janitormanager.domain.entity.ActivityAssignment
import de.davidhuh.janitormanager.domain.entity.Employee
import de.davidhuh.janitormanager.domain.entity.Gardener
import de.davidhuh.janitormanager.domain.valueobjects.ActivityType
import de.davidhuh.janitormanager.domain.valueobjects.Sector
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

internal class ActivityTest {
	private val employee = Gardener(
		UUID.randomUUID(),
		"Test",
		"Troll",
		LocalDate(2022, 2, 14)
	)
	private val activityAssignment = ActivityAssignment(
		mutableListOf(employee),
		LocalDate(1990, 1, 1),
		LocalDate(2022, 12, 31)
	)

	private val activity = Activity(
		LocalDate(1990, 1, 1),
		365,
		ActivityType("Test", Sector.INDOOR),
		mutableListOf<ActivityAssignment>()
	)

	@Test
	fun addActivityAssignment() {
		assertEquals(activity.activityAssignmentList.size, 0)
		activity.addActivityAssignment(activityAssignment)
		assertEquals(activity.activityAssignmentList.size, 1)
	}
}
