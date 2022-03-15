package de.davidhuh.janitormanager.domain.entity.todo

import de.davidhuh.janitormanager.domain.entity.activity.Activity
import kotlinx.datetime.LocalDate
import kotlinx.datetime.daysUntil
import kotlinx.serialization.Serializable
import java.util.*
import java.util.Calendar.*

@Serializable
data class Todo(
	val activity: Activity,
	val date: LocalDate,
	var doneDate: LocalDate? = null,
) {
	private val now = LocalDate(
		getInstance().get(YEAR),
		getInstance().get(MONTH)+1,
		getInstance().get(DAY_OF_MONTH)
	)

	fun isDone(): Boolean {
		return (this.doneDate != null)
	}

	fun isOverdue(): Boolean {
		return date < now
	}

	fun changeStatus() {
		this.doneDate = if (this.doneDate == null) {
			now
		} else {
			null
		}
	}

	fun toStringWithOverdueDays(): String {
		return "$this (${date.daysUntil(now)} days overdue)"
	}

	override fun toString(): String {
		val doneText = if (this.isDone()) "✅" else "❌"
		val overdueText = if (!this.isDone()) (if (this.isOverdue()) "⌛" else "⏳") else ""
		return "${this.date} $overdueText $doneText".replace("  "," ")
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
