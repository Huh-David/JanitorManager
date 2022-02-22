package de.davidhuh.janitormanager.domain

import kotlinx.datetime.LocalDate
import java.util.*

class Gardener(
	employeeId: UUID,
	preName: String,
	surName: String,
	registrationDate: LocalDate,
) : Employee(employeeId, preName, surName, registrationDate) {
	override fun toString(): String {
		return "Gardener $preName $surName since ${registrationDate.toString()}\n${employeeId.toString()}"
	}
}
