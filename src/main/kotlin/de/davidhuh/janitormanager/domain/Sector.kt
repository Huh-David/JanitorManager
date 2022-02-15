package de.davidhuh.janitormanager.domain

enum class Sector() {
	INDOOR {},
	OUTDOOR {},
	SOMEWHERE {};

	override fun toString(): String {
		// TODO change from Uppercase to normalized string
		return super.toString()
	}
}
