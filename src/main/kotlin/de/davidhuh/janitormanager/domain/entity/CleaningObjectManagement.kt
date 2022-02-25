package de.davidhuh.janitormanager.domain.entity

import de.davidhuh.janitormanager.domain.valueobject.Address
import kotlinx.serialization.Serializable

@Serializable
data class CleaningObjectManagement(
	val firstName: String,
	val lastName: String,
	val phone: String,
	val email: String,
	var address: Address,
) {
	fun changeAddress(newAddress: Address) {
		this.address = newAddress
	}

	override fun toString(): String {
		return "$firstName $lastName - $address $email $phone"
	}

	fun generalInformation(): String {
		return "$firstName $lastName"
	}
}
