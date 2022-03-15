package de.davidhuh.janitormanager.domain.entity.cleaningobject

import de.davidhuh.janitormanager.domain.entity.cleaningobjectmanagement.CleaningObjectManagement
import de.davidhuh.janitormanager.domain.entity.activity.ActivityAggregate
import de.davidhuh.janitormanager.domain.valueobject.address.Address
import de.davidhuh.janitormanager.domain.valueobject.space.Space
import kotlinx.serialization.Serializable

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
