package de.davidhuh.janitormanager.domain

import de.davidhuh.janitormanager.service.HelperService
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
class Employee(
	@Serializable(with = HelperService.UUIDSerializer::class)
	val employeeId: UUID,
	val preName: String,
	val surName: String,
	val registrationDate: LocalDate
) {
}
