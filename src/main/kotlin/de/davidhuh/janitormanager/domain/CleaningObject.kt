package de.davidhuh.janitormanager.domain

import kotlinx.serialization.Serializable
import de.davidhuh.janitormanager.repository.ActivityRepo

@Serializable
class CleaningObject(
	var address: Address,
	var cleaningObjectManagement: CleaningObjectManagement,
	var activityRepoList: MutableList<ActivityRepo>,
	var cleaningObjectType: CleaningObjectType = CleaningObjectType.HOUSE,
) {
	override fun toString(): String = "$cleaningObjectType - $address"

	fun toAlternativeString(): String =
		"$cleaningObjectType - ${address.city} ${address.zipcode} - ${address.street} ${address.house_number}"

	fun toSortString(): String = "${address.city} ${address.zipcode} ${address.street} ${address.house_number}"
}
