package de.davidhuh.janitormanager.domain

data class Space(
	val width: Float,
	val length: Float,
) {
	val value: Float = width * length

	override fun toString(): String = "$value m²"
}
