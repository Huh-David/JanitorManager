package de.davidhuh.janitormanager.domain

import java.time.LocalDate

data class Todo(
	val activity: Activity,
	val date: LocalDate,
	var done: Boolean = false,
	var overdue: Boolean = false,
) {
	fun changeStatus() {
		this.done != this.done
	}

	override fun toString(): String {
		val doneText = if (this.done) "✅" else "❌"
		val overdueText = if (this.overdue) "⌛" else "⏳"
		return "${this.date} $overdueText $doneText"
	}
}
