package de.davidhuh.janitormanager.domain

data class Space(
	val width: Int,
	val height: Int,
) {
	private val value: Int = width * height

	override fun toString(): String = value.toString()
}
