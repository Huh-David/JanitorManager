package de.davidhuh.janitormanager.domain

data class Address(
	val street: String?,
	val house_number: String?,
	val zipcode: String?,
	val city: String?,
) {
	override fun toString(): String = "$street $house_number - $zipcode $city"
}
