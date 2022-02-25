package de.davidhuh.janitormanager.domain.entity

import de.davidhuh.janitormanager.domain.valueobject.Address
import de.davidhuh.janitormanager.domain.valueobject.CleaningObjectType
import de.davidhuh.janitormanager.domain.valueobject.Space
import kotlinx.serialization.Serializable
import de.davidhuh.janitormanager.domain.entity.aggregate.ActivityAggregate

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
