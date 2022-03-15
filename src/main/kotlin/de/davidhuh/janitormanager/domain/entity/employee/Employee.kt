package de.davidhuh.janitormanager.domain.entity.employee

import de.davidhuh.janitormanager.domain.serializer.UUIDSerializer
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
sealed class Employee {
	@Serializable(with = UUIDSerializer::class)
	abstract val employeeId: UUID
	abstract val preName: String
	abstract val surName: String
	abstract val registrationDate: LocalDate
}
