package de.davidhuh.janitormanager.repository

import de.davidhuh.janitormanager.domain.*
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

internal class EmployeeRepoTest {
	private val employee = Employee(
		UUID.randomUUID(),
		"Test",
		"Troll",
		LocalDate(2022, 2, 14)
	)

	private val activityAssignmentList = mutableListOf<ActivityAssignment>(ActivityAssignment(
		mutableListOf(employee),
		LocalDate(1990, 1, 1),
		LocalDate(2022, 12, 31)
	))

	private val activity = Activity(
		LocalDate(1990, 1, 1),
		365,
		ActivityType("Test", Sector.INDOOR),
		activityAssignmentList
	)

	private val activityList = mutableListOf<Activity>(activity)
	private val activityRepo = ActivityRepo(ActivityType("Test", Sector.SOMEWHERE), activityList)

	private val activityRepoList = mutableListOf<ActivityRepo>(activityRepo)


	private val employeeRepo = EmployeeRepo(activityRepoList)

	@Test
	fun getAssignedActivities() {
		assertEquals(activityList, employeeRepo.getAssignedActivities(employee))
	}
}
