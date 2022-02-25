package de.davidhuh.janitormanager.domain.entity

import de.davidhuh.janitormanager.domain.Serializer
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class CleaningSpecialist(
	@Serializable(with = Serializer.UUIDSerializer::class)
	override val employeeId: UUID,
	override val preName: String,
	override val surName: String,
	override val registrationDate: LocalDate,
) : Employee() {
	override fun toString(): String {
		return "Cleaning Specialist $preName $surName since ${registrationDate.toString()}\n${employeeId.toString()}"
	}
}