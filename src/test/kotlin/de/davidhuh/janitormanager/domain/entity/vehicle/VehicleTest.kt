package de.davidhuh.janitormanager.domain.entity.vehicle

import de.davidhuh.janitormanager.domain.entity.employee.CleaningSpecialist
import de.davidhuh.janitormanager.domain.entity.employee.Gardener
import de.davidhuh.janitormanager.domain.entity.vehicle.Vehicle
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

internal class VehicleTest {
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
		0f,
		Vehicle.FuelType.PETROL
	)

	@Test
	fun addKilometers() {
		assertEquals(vehicle.kilometers, 0f)
		vehicle.addKilometers(100f)
		assertEquals(vehicle.kilometers, 100f)
		vehicle.addKilometers(100f)
		assertEquals(vehicle.kilometers, 200f)
	}

	@Test
	fun subtractKilometers() {
		assertEquals(vehicle.kilometers, 0f)
		vehicle.addKilometers(200f)
		assertEquals(vehicle.kilometers, 200f)
		vehicle.subtractKilometers(100f)
		assertEquals(vehicle.kilometers, 100f)
		vehicle.subtractKilometers(100f)
		assertEquals(vehicle.kilometers, 0f)
	}

	@Test
	fun changeUser() {
		assertEquals(vehicle.lastUsedBy, employee)
		vehicle.changeUser(employee2)
		assertEquals(vehicle.lastUsedBy, employee2)
	}

	@Test
	fun changeFuelType() {
		assertEquals(vehicle.fuelType, Vehicle.FuelType.PETROL)
		vehicle.changeFuelType(Vehicle.FuelType.LPG)
		assertEquals(vehicle.fuelType, Vehicle.FuelType.LPG)
	}
}
