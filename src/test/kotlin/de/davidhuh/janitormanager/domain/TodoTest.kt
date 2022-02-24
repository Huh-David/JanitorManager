package de.davidhuh.janitormanager.domain

import de.davidhuh.janitormanager.domain.entity.Activity
import de.davidhuh.janitormanager.domain.entity.ActivityAssignment
import de.davidhuh.janitormanager.domain.entity.Employee
import de.davidhuh.janitormanager.domain.entity.Todo
import de.davidhuh.janitormanager.domain.valueobjects.ActivityType
import de.davidhuh.janitormanager.domain.valueobjects.Sector
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

internal class TodoTest {
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

	private val todoDone = Todo(activity, LocalDate(2020, 1, 1), LocalDate(2020, 1, 1))
	private val todoNotDone = Todo(activity, LocalDate(2020, 1, 1))

	@Test
	fun isDone() {
		assert(todoDone.isDone())
		assert(!todoNotDone.isDone())
	}

	@Test
	fun isOverdue() {
		assert(todoDone.isOverdue())
		assert(todoNotDone.isOverdue())
	}

	@Test
	fun changeStatus() {
		todoDone.changeStatus()
		assert(!todoDone.isDone())
		todoDone.changeStatus()
		assert(todoDone.isDone())
	}
}
