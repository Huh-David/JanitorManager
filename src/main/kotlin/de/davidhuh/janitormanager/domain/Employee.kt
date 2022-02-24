package de.davidhuh.janitormanager.domain

import kotlinx.datetime.LocalDate
import java.util.UUID

interface Employee {
	val employeeId: UUID
	val preName: String
	val surName: String
	val registrationDate: LocalDate
}
