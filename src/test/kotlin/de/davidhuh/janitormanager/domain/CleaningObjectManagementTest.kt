package de.davidhuh.janitormanager.domain

import de.davidhuh.janitormanager.repository.ActivityRepo
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

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
