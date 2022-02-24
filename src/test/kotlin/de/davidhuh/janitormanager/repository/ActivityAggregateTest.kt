package de.davidhuh.janitormanager.repository

import de.davidhuh.janitormanager.repository.aggregate.ActivityAggregate
import de.davidhuh.janitormanager.domain.*
import de.davidhuh.janitormanager.domain.entity.*
import de.davidhuh.janitormanager.domain.valueobjects.ActivityType
import de.davidhuh.janitormanager.domain.valueobjects.Address
import de.davidhuh.janitormanager.domain.valueobjects.CleaningObjectType
import de.davidhuh.janitormanager.domain.valueobjects.Sector
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Test
import java.util.*

internal class ActivityAggregateTest {
	private val address = Address("Str.", "2", "12345", "City")
	private val cleaningObjectManagement = CleaningObjectManagement(
		"Prename",
		"Surname",
		"12345",
		"mail@mail.com",
		address
	)

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
	private val incompatibleActivity = Activity(
		LocalDate(1990, 1, 1),
		365,
		ActivityType("Test", Sector.OUTDOOR),
		activityAssignmentList
	)

	private val activityToAdd = Activity(
		LocalDate(1990, 1, 2),
		365,
		ActivityType("Test", Sector.INDOOR),
		activityAssignmentList
	)

	private val activityList = mutableListOf<Activity>(activity)
	private val activityAggregate = ActivityAggregate(ActivityType("Test", Sector.INDOOR), activityList)


	private val cleaningObject = CleaningObject(
		address,
		cleaningObjectManagement,
		mutableListOf(activityAggregate),
		CleaningObjectType.HOUSE
	)


	@Test
	fun checkCompatibility() {
		assert(activityAggregate.checkCompatibility(activity))
		assert(!activityAggregate.checkCompatibility(incompatibleActivity))
	}

	@Test
	fun checkActivityExistence() {
		assert(activityAggregate.checkActivityExistence(activity))
		assert(!activityAggregate.checkActivityExistence(activityToAdd))
	}

	@Test
	fun addActivity() {
		assert(activityAggregate.addActivity(activityToAdd))
		assert(activityAggregate.checkActivityExistence(activityToAdd))
	}

	@Test
	fun getAllTodos() {
		assert(activityAggregate.getAllTodos(cleaningObject).size > 0)
	}

	@Test
	fun getFirstTodo() {
		assert(activityAggregate.getFirstTodo(activityAggregate.getAllTodos(cleaningObject)).date == LocalDate(1990, 1, 1))
	}
}
