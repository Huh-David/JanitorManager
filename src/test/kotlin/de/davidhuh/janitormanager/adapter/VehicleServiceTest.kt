package de.davidhuh.janitormanager.adapter

import de.davidhuh.janitormanager.application.vehicle.VehicleService
import de.davidhuh.janitormanager.domain.entity.carpool.Carpool
import de.davidhuh.janitormanager.domain.entity.employee.CleaningSpecialist
import de.davidhuh.janitormanager.domain.entity.employee.Gardener
import de.davidhuh.janitormanager.domain.entity.vehicle.Vehicle
import de.davidhuh.janitormanager.domain.valueobject.address.Address
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

internal class VehicleServiceTest {

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

	private val vehicleService = VehicleService(
		carpool
	)


	@Test
	fun getAllVehiclesWithLoadingArea() {
		assertEquals(vehicleService.getAllVehiclesWithLoadingArea().size, 0)
		assertEquals(vehicleService.getAllVehiclesWithLoadingArea(true).size, 1)
	}

	@Test
	fun getAllVehiclesWithMoreThan() {
		assertEquals(vehicleService.getAllVehiclesWithMoreThan(100000f).size, 1)
		assertEquals(vehicleService.getAllVehiclesWithMoreThan(1000000f).size, 0)
	}

	@Test
	fun getAllVehiclesLastUsedBy() {
		assertEquals(vehicleService.getAllVehiclesLastUsedBy(employee).size, 1)
		assertEquals(vehicleService.getAllVehiclesLastUsedBy(employee2).size, 0)
	}

	@Test
	fun getAllVehiclesCurrentlyUsed() {
		assertEquals(vehicleService.getAllVehiclesCurrentlyUsed().size, 1)
		assertEquals(vehicleService.getAllVehiclesCurrentlyUsed(true).size, 0)
	}
}
