package de.davidhuh.janitormanager.domain.entity

import de.davidhuh.janitormanager.domain.entity.ActivityAssignment
import de.davidhuh.janitormanager.domain.entity.CleaningSpecialist
import de.davidhuh.janitormanager.domain.entity.Gardener
import de.davidhuh.janitormanager.domain.valueobject.Address
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.*

internal class ActivityAssignmentTest {
	private val address = Address("Str.", "2", "12345", "City")

	private val employee = Gardener(
		UUID.randomUUID(),
		"Test",
		"Troll",
		LocalDate(2022, 2, 14)
	)

	private val employee2 = CleaningSpecialist(
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

	@Test
	fun addEmployee() {
		assertEquals(activityAssignment.employeeList.size, 1)
		activityAssignment.addEmployee(employee2)
		assertEquals(activityAssignment.employeeList.size, 2)
	}

	@Test
	fun removeEmployee() {
		assertEquals(activityAssignment.employeeList.size, 1)
		activityAssignment.removeEmployee(employee)
		assertEquals(activityAssignment.employeeList.size, 0)
	}

	@Test
	fun changeStartDate() {
		assertNotEquals(activityAssignment.startDate, LocalDate(2002, 2, 2))
		activityAssignment.changeStartDate(LocalDate(2002, 2, 2))
		assertEquals(activityAssignment.startDate, LocalDate(2002, 2, 2))
	}

	@Test
	fun changeEndDate() {
		assertNotEquals(activityAssignment.endDate, LocalDate(2002, 2, 2))
		activityAssignment.changeEndDate(LocalDate(2002, 2, 2))
		assertEquals(activityAssignment.endDate, LocalDate(2002, 2, 2))
	}
}
