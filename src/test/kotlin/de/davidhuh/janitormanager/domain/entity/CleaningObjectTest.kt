package de.davidhuh.janitormanager.domain.entity

import de.davidhuh.janitormanager.domain.entity.activity.Activity
import de.davidhuh.janitormanager.domain.entity.activityassignment.ActivityAssignment
import de.davidhuh.janitormanager.domain.entity.activity.ActivityAggregate
import de.davidhuh.janitormanager.domain.entity.activity.ActivityType
import de.davidhuh.janitormanager.domain.entity.cleaningobject.CleaningObject
import de.davidhuh.janitormanager.domain.entity.cleaningobject.CleaningObjectType
import de.davidhuh.janitormanager.domain.entity.cleaningobjectmanagement.CleaningObjectManagement
import de.davidhuh.janitormanager.domain.entity.employee.Gardener
import de.davidhuh.janitormanager.domain.valueobject.address.Address
import de.davidhuh.janitormanager.domain.valueobject.sector.Sector
import de.davidhuh.janitormanager.domain.valueobject.space.Space
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
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
		assertEquals(cleaningObject.address, address)
		cleaningObject.changeAddress(newAddress)
		assertEquals(cleaningObject.address, newAddress)
	}

	@Test
	fun changeCleaningObjectManagement() {
		assertEquals(cleaningObject.cleaningObjectManagement, cleaningObjectManagement)
		cleaningObject.changeCleaningObjectManagement(newCleaningObjectManagement)
		assertEquals(cleaningObject.cleaningObjectManagement, newCleaningObjectManagement)
	}

	@Test
	fun changeCleaningObjectType() {
		assertEquals(cleaningObject.cleaningObjectType, CleaningObjectType.HOUSE)
		cleaningObject.changeCleaningObjectType(CleaningObjectType.APARTMENT)
		assertEquals(cleaningObject.cleaningObjectType, CleaningObjectType.APARTMENT)
	}

	@Test
	fun calculateEstimatedCosts() {
		assertEquals(cleaningObject.calculateEstimatedCosts(space, 1f), 10000f)
		assertEquals(cleaningObject.calculateEstimatedCosts(space, 10f), 100000f)
	}
}
