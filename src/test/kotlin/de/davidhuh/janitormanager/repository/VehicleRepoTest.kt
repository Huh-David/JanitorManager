package de.davidhuh.janitormanager.repository

import de.davidhuh.janitormanager.domain.*
import de.davidhuh.janitormanager.domain.entity.Carpool
import de.davidhuh.janitormanager.domain.entity.Employee
import de.davidhuh.janitormanager.domain.entity.Vehicle
import de.davidhuh.janitormanager.domain.valueobjects.Address
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

internal class VehicleRepoTest {

	private val address = Address("Str.", "2", "12345", "City")

	private val employee = Employee(
		UUID.randomUUID(),
		"Test",
		"Troll",
		LocalDate(2022, 2, 14)
	)

	private val employee2 = Employee(
		UUID.randomUUID(),
		"Test",
		"Troll",
		LocalDate(2022, 2, 14)
	)

	private val vehicle = Vehicle(
		"123a",
		"Volkswagen",
		"Polo",
		false,
		employee,
		true,
		123000f,
		Vehicle.FuelType.PETROL
	)

	private val carpool = Carpool(
		address,
		mutableListOf(vehicle)
	)

	private val vehicleRepo = VehicleRepo(
		carpool
	)


	@Test
	fun getAllVehiclesWithLoadingArea() {
		assertEquals(vehicleRepo.getAllVehiclesWithLoadingArea().size, 0)
		assertEquals(vehicleRepo.getAllVehiclesWithLoadingArea(true).size, 1)
	}

	@Test
	fun getAllVehiclesWithMoreThan() {
		assertEquals(vehicleRepo.getAllVehiclesWithMoreThan(100000f).size, 1)
		assertEquals(vehicleRepo.getAllVehiclesWithMoreThan(1000000f).size, 0)
	}

	@Test
	fun getAllVehiclesLastUsedBy() {
		assertEquals(vehicleRepo.getAllVehiclesLastUsedBy(employee).size, 1)
		assertEquals(vehicleRepo.getAllVehiclesLastUsedBy(employee2).size, 0)
	}

	@Test
	fun getAllVehiclesCurrentlyUsed() {
		assertEquals(vehicleRepo.getAllVehiclesCurrentlyUsed().size, 1)
		assertEquals(vehicleRepo.getAllVehiclesCurrentlyUsed(true).size, 0)
	}
}
