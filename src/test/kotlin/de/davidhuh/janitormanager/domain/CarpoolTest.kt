package de.davidhuh.janitormanager.domain

import de.davidhuh.janitormanager.domain.entity.Carpool
import de.davidhuh.janitormanager.domain.entity.Employee
import de.davidhuh.janitormanager.domain.entity.Vehicle
import de.davidhuh.janitormanager.domain.valueobjects.Address
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

internal class CarpoolTest {
	private val address = Address("Str.", "2", "12345", "City")
	private val newAddress = Address("Str.", "3", "12345", "City")

	private val employee = Employee(
		UUID.randomUUID(),
		"Test",
		"Troll",
		LocalDate(2022, 2, 14)
	)

	private val vehicleA = Vehicle(
		"123a",
		"Volkswagen",
		"Polo",
		false,
		employee,
		true,
		123000f,
		Vehicle.FuelType.PETROL
	)
	private val vehicleB = Vehicle(
		"123b",
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
		mutableListOf(vehicleA)
	)


	@Test
	fun changeAddress() {
		assertEquals(carpool.address, address)
		assertNotEquals(carpool.address, newAddress)
		carpool.changeAddress(newAddress)
		assertNotEquals(carpool.address, address)
		assertEquals(carpool.address, newAddress)
	}

	@Test
	fun addVehicleToPool() {
		assertEquals(carpool.vehicles.size, 1)
		carpool.addVehicleToPool(vehicleB)
		assertEquals(carpool.vehicles.size, 2)
	}

	@Test
	fun removeVehicleFromPool() {
		assertEquals(carpool.vehicles.size, 1)
		carpool.removeVehicleFromPool(vehicleA)
		assertEquals(carpool.vehicles.size, 0)
	}
}
