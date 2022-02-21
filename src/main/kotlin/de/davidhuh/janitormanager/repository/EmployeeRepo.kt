package de.davidhuh.janitormanager.repository

import de.davidhuh.janitormanager.domain.Activity
import de.davidhuh.janitormanager.domain.Employee

class EmployeeRepo(
	var activityRepoList: List<ActivityRepo>,
) {
	fun getAssignedActivities(employee: Employee): List<Activity> {
		val activityList = mutableListOf<Activity>()

		for (activityRepo in this.activityRepoList) {
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
