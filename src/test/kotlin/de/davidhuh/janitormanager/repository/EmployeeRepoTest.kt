package de.davidhuh.janitormanager.repository

import de.davidhuh.janitormanager.repository.aggregate.ActivityAggregate
import de.davidhuh.janitormanager.domain.*
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

internal class EmployeeRepoTest {
	private val employee = Gardener(
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
	private val activityAggregate = ActivityAggregate(ActivityType("Test", Sector.SOMEWHERE), activityList)

	private val activityAggregateList = mutableListOf<ActivityAggregate>(activityAggregate)


	private val employeeRepo = EmployeeRepo(activityAggregateList)

	@Test
	fun getAssignedActivities() {
		assertEquals(activityList, employeeRepo.getAssignedActivities(employee))
	}
}
