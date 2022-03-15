package de.davidhuh.janitormanager.application.employee

import de.davidhuh.janitormanager.domain.entity.activity.Activity
import de.davidhuh.janitormanager.domain.entity.employee.Employee
import de.davidhuh.janitormanager.domain.entity.activity.ActivityAggregate

class EmployeeService(
	var activityAggregateList: List<ActivityAggregate>,
) {
	fun getAssignedActivities(employee: Employee): List<Activity> {
		val activityList = mutableListOf<Activity>()

		for (activityRepo in this.activityAggregateList) {
			for (activity in activityRepo.activityList) {
				for (assignment in activity.activityAssignmentList) {
					if (assignment.employeeList.contains(employee)) {
						activityList.add(activity)
					}
				}
			}
		}

		return activityList
	}
}
