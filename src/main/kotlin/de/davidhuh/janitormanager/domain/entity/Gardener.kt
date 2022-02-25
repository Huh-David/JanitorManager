package de.davidhuh.janitormanager.domain.entity

import de.davidhuh.janitormanager.service.HelperService
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class Gardener(
	@Serializable(with = HelperService.UUIDSerializer::class)
	override val employeeId: UUID,
	override val preName: String,
	override val surName: String,
	override val registrationDate: LocalDate,
) : Employee() {
	override fun toString(): String {
		return "Gardener $preName $surName since ${registrationDate.toString()}\n${employeeId.toString()}"
	}
}
