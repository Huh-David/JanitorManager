package de.davidhuh.janitormanager.domain

import java.time.LocalDate

data class Todo(
	val activity: Activity,
	val date: LocalDate,
	var done: Boolean = false,
) {
	fun changeStatus() {
		this.done != this.done
	}

	override fun toString(): String {
		var done = if (this.done) "✅" else "❌"
		return "${this.date} $done"
	}
}
