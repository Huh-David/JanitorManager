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
	fun changeAddress(address: Address) {
		this.address = address
	}

	fun changeCleaningObjectManagement(cleaningObjectManagement: CleaningObjectManagement) {
		this.cleaningObjectManagement = cleaningObjectManagement
	}

	fun changeCleaningObjectType(cleaningObjectType: CleaningObjectType) {
		this.cleaningObjectType = cleaningObjectType
	}

	override fun toString(): String = "$cleaningObjectType - $address"

	fun toAlternativeString(): String =
		"$cleaningObjectType - ${address.city} ${address.zipcode} - ${address.street} ${address.houseNumber}"
}
