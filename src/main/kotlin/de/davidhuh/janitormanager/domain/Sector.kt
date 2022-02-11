package de.davidhuh.janitormanager.domain

data class Sector(
	val name: String,
) {
	override fun toString(): String = "$name"
}
