package de.davidhuh.janitormanager.domain
import kotlinx.serialization.Serializable

@Serializable
data class Address(
	val street: String?,
	val house_number: String?,
	val zipcode: String?,
	val city: String?,
) {
	override fun toString(): String = "$street $house_number - $zipcode $city"

	fun toStringTwoLines(): String = "$street $house_number\n$zipcode $city"
}
