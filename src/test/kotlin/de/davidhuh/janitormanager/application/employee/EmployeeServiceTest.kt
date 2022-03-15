package de.davidhuh.janitormanager.application.employee

import de.davidhuh.janitormanager.application.employee.EmployeeService
import de.davidhuh.janitormanager.domain.entity.activity.Activity
import de.davidhuh.janitormanager.domain.entity.activityassignment.ActivityAssignment
import de.davidhuh.janitormanager.domain.entity.employee.Gardener
import de.davidhuh.janitormanager.domain.entity.activity.ActivityAggregate
import de.davidhuh.janitormanager.domain.entity.activity.ActivityType
import de.davidhuh.janitormanager.domain.valueobject.sector.Sector
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

internal class EmployeeServiceTest {
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


	private val employeeService = EmployeeService(activityAggregateList)

	@Test
	fun getAssignedActivities() {
		assertEquals(activityList, employeeService.getAssignedActivities(employee))
	}
}
