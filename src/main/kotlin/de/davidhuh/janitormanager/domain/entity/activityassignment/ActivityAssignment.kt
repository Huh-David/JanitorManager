package de.davidhuh.janitormanager.domain.entity.activityassignment

import de.davidhuh.janitormanager.domain.entity.employee.Employee
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable


@Serializable
data class ActivityAssignment(
	val employeeList: MutableList<Employee>,
	var startDate: LocalDate, // range of date when the employee(s) is assigned to the activity
	var endDate: LocalDate,
) {
	fun addEmployee(employee: Employee) {
		employeeList.add(employee)
	}

	fun removeEmployee(employee: Employee) {
		employeeList.remove(employee)
	}

	fun changeStartDate(startDate: LocalDate) {
		this.startDate = startDate
	}

	fun changeEndDate(endDate: LocalDate) {
		this.endDate = endDate
	}
}
