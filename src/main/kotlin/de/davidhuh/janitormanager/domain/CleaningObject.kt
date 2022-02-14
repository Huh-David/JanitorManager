package de.davidhuh.janitormanager.domain

import de.davidhuh.janitormanager.repository.ActivityRepo


class CleaningObject(
	var address: Address,
	var cleaningObjectManagement: CleaningObjectManagement,
	var activityList: MutableList<Activity>,
	var activityRepoList: MutableList<ActivityRepo>
) {
	fun addActivity(activity: Activity) {
		activityList.add(activity)
	}

	fun removeActivity(activity: Activity) {
		activityList.remove(activity)
	}

	override fun toString(): String = "\uD83C\uDFE0 - $address" // 🏠
}
