package de.davidhuh.janitormanager.domain

data class Space(
	val width: Float,
	val height: Float,
) {
	val value: Float = width * height

	override fun toString(): String = "$value m²"
}
