package de.davidhuh.janitormanager.domain

import de.davidhuh.janitormanager.repository.ActivityRepo


class CleaningObject(
	var address: Address,
	var cleaningObjectManagement: CleaningObjectManagement,
	var activityRepoList: MutableList<ActivityRepo>,
	var cleaningObjectType: CleaningObjectType = CleaningObjectType.HOUSE,
) {
	override fun toString(): String = if (cleaningObjectType == CleaningObjectType.APPARTMENT) {
		"\uD83D\uDECB - $address" // 🛋️
	} else if (cleaningObjectType == CleaningObjectType.HOUSE) {
		"\uD83C\uDFD8 - $address" // 🏘️
	} else {
		"$address"
	}
}
