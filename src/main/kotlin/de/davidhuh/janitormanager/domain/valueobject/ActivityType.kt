package de.davidhuh.janitormanager.domain.valueobject

import kotlinx.serialization.Serializable

@Serializable
data class ActivityType(
	val name: String,
	val sector: Sector,
) {
	override fun toString(): String = "$sector - $name"

	fun toStringTwoLines(): String = "$sector\n$name"
}
