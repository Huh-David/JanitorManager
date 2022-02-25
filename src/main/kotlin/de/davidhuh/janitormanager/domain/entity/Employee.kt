package de.davidhuh.janitormanager.domain.entity

import de.davidhuh.janitormanager.adapter.service.HelperService
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
sealed class Employee {
	@Serializable(with = HelperService.UUIDSerializer::class)
	abstract val employeeId: UUID
	abstract val preName: String
	abstract val surName: String
	abstract val registrationDate: LocalDate
}
