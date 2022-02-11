package de.davidhuh.janitormanager.domain

class CleaningObject(
	var address: Address,
	var cleaningObjectManagement: CleaningObjectManagement,
	var activityList: MutableList<Activity>,
) {
	fun addActivity(activity: Activity) {
		activityList.add(activity)
	}

	fun removeActivity(activity: Activity) {
		activityList.remove(activity)
	}

	override fun toString(): String = "\uD83C\uDFE0 - $address" // 🏠
}
