package de.davidhuh.janitormanager.domain.valueobject.sector

enum class Sector() {
	INDOOR {},
	OUTDOOR {},
	SOMEWHERE {};

	override fun toString(): String {
		return "${super.toString().subSequence(0, 1)}${super.toString().subSequence(1, super.toString().length).toString().lowercase()}"
	}
}
