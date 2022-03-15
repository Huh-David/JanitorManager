package de.davidhuh.janitormanager.domain.entity.activity

import kotlinx.serialization.Serializable

@Serializable
class ActivityAggregate(
	var activityType: ActivityType,
	var activityList: MutableList<Activity>,
) {
	fun checkCompatibility(activity: Activity): Boolean {
		return activity.activityType == activityType
	}

	fun checkActivityExistence(activity: Activity): Boolean {
		return !activityList.none {
			it.startDate == activity.startDate && it.intervalInDays == activity.intervalInDays
		}
	}

	fun addActivity(activity: Activity): Boolean {
		if (checkCompatibility(activity)) {
			if (!checkActivityExistence(activity)) {
				activityList.add(activity)
				return true
			}
		}
		return false
	}
}
