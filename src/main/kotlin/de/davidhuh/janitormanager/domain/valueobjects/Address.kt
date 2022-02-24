package de.davidhuh.janitormanager.domain.valueobjects
import kotlinx.serialization.Serializable

@Serializable
data class Address(
	var street: String,
	var houseNumber: String,
	var zipcode: String,
	var city: String,
) {
	override fun toString(): String = "$street $houseNumber - $zipcode $city"

	fun toStringTwoLines(): String = "$street $houseNumber\n$zipcode $city"

	fun toSortString(): String = "$city $zipcode $street $houseNumber"
}
