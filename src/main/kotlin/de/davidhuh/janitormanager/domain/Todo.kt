package de.davidhuh.janitormanager.domain

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import java.util.Calendar
import java.util.Calendar.*
import java.util.Objects

@Serializable
data class Todo(
	val activity: Activity,
	val date: LocalDate,
	var done: Boolean = false,
) {
	val overdue = date < LocalDate(
		getInstance().get(YEAR),
		getInstance().get(MONTH),
		getInstance().get(DAY_OF_MONTH)
	)

	fun changeStatus() {
		this.done = !this.done
	}

	override fun toString(): String {
		val doneText = if (this.done) "✅" else "❌"
		val overdueText = if (this.overdue) "⌛" else "⏳"
		return "${this.date} $overdueText $doneText"
	}

	override fun hashCode(): Int {
		return Objects.hash(activity, date)
	}

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as Todo

		if (activity != other.activity) return false
		if (date != other.date) return false

		return true
	}
}
