package de.davidhuh.janitormanager.repository

import de.davidhuh.janitormanager.repository.aggregate.ActivityAggregate
import de.davidhuh.janitormanager.domain.entity.Activity
import de.davidhuh.janitormanager.domain.entity.Employee

class EmployeeRepo(
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
