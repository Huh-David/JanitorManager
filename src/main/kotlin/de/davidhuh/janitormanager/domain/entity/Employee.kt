package de.davidhuh.janitormanager.domain.entity

import de.davidhuh.janitormanager.domain.Serializer
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
sealed class Employee {
	@Serializable(with = Serializer.UUIDSerializer::class)
	abstract val employeeId: UUID
	abstract val preName: String
	abstract val surName: String
	abstract val registrationDate: LocalDate
}
