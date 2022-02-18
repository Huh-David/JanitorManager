package de.davidhuh.janitormanager.domain

import kotlinx.serialization.Serializable

@Serializable
data class ActivityType(
	val name: String,
	val sector: Sector,
) {
	override fun toString(): String = "$sector - $name"

	fun toStringTwoLines(): String = "$sector\n$name"
}
