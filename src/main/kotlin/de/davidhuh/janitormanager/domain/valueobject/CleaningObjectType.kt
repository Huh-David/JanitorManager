package de.davidhuh.janitormanager.domain.valueobject

enum class CleaningObjectType() {
	APARTMENT {
		override fun toString(): String {
			return "\uD83D\uDECB" // 🛋️
		}
	},
	HOUSE {
		override fun toString(): String {
			return "\uD83C\uDFD8" // 🏘️
		}
	}
}
