package de.davidhuh.janitormanager.domain.entity

import de.davidhuh.janitormanager.domain.valueobject.Address
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CleaningObjectManagementTest {
	private val address = Address("Str.", "2", "12345", "City")

	private val cleaningObjectManagement = CleaningObjectManagement(
		"Prename",
		"Surname",
		"12345",
		"mail@mail.com",
		address
	)

	private val newAddress = Address("Str.", "3", "12345", "City")

	@Test
	fun changeAddress() {
		assertEquals(cleaningObjectManagement.address, address)
		cleaningObjectManagement.changeAddress(newAddress)
		assertEquals(cleaningObjectManagement.address, newAddress)
	}
}
