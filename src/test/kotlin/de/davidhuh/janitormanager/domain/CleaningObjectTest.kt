package de.davidhuh.janitormanager.domain

import de.davidhuh.janitormanager.repository.ActivityRepo
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

internal class CleaningObjectTest {
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
	private val activityRepo = ActivityRepo(ActivityType("Test", Sector.INDOOR), activityList)


	private val cleaningObject = CleaningObject(
		address,
		cleaningObjectManagement,
		mutableListOf(activityRepo),
		CleaningObjectType.HOUSE
	)

	private val newAddress = Address("Str.", "3", "12345", "City")
	private val newCleaningObjectManagement = CleaningObjectManagement(
		"Prename",
		"Surname",
		"12345",
		"mail@mail.com",
		newAddress
	)
	private val space = Space(100f, 100f)

	@Test
	fun changeAddress() {
		cleaningObject.changeAddress(newAddress)
		assertEquals(cleaningObject.address, newAddress)
	}

	@Test
	fun changeCleaningObjectManagement() {
		cleaningObject.changeCleaningObjectManagement(newCleaningObjectManagement)
		assertEquals(cleaningObject.cleaningObjectManagement, newCleaningObjectManagement)
	}

	@Test
	fun changeCleaningObjectType() {
		cleaningObject.changeCleaningObjectType(CleaningObjectType.APARTMENT)
		assertEquals(cleaningObject.cleaningObjectType, CleaningObjectType.APARTMENT)
	}

	@Test
	fun calculateEstimatedCosts() {
		assertEquals(cleaningObject.calculateEstimatedCosts(space, 1f), 10000f)
	}
}