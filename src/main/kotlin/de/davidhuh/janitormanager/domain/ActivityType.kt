package de.davidhuh.janitormanager.domain

data class ActivityType(
	val name: String,
	val sector: Sector,
) {
	override fun toString(): String = "$sector - $name"
}
