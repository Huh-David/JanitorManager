package de.davidhuh.janitormanager.domain

import kotlinx.serialization.Serializable
import de.davidhuh.janitormanager.aggregate.ActivityAggregate

@Serializable
class CleaningObject(
	var address: Address,
	var cleaningObjectManagement: CleaningObjectManagement,
	var activityAggregateList: MutableList<ActivityAggregate>,
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

	fun calculateEstimatedCosts(space: Space, pricePerSquareMeter: Float): Float {
		return space.value * pricePerSquareMeter
	}

	override fun toString(): String = "$cleaningObjectType - $address"

	fun toAlternativeString(): String =
		"$cleaningObjectType - ${address.city} ${address.zipcode} - ${address.street} ${address.houseNumber}"
}
