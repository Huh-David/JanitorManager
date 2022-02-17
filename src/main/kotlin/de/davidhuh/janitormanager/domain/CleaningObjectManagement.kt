package de.davidhuh.janitormanager.domain
import kotlinx.serialization.Serializable

@Serializable
data class CleaningObjectManagement(
	val firstName: String,
	val lastName: String,
	val phone: String,
	val email: String,
	val address: Address,
) {
	override fun toString(): String {
		return "$firstName $lastName - $address $email $phone"
	}

	fun generalInformation(): String {
		return "$firstName $lastName"
	}
}
