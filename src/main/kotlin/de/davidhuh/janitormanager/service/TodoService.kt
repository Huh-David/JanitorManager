package de.davidhuh.janitormanager.service

import de.davidhuh.janitormanager.domain.ActivityType
import de.davidhuh.janitormanager.domain.CleaningObject
import de.davidhuh.janitormanager.domain.Todo
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException

class TodoService(
	private val cleaningObject: CleaningObject,
) {
	private fun makeDirectories() {
		if (!File(DIRPATH).exists()) {
			File(DIRPATH).mkdirs()
		}
	}

	private fun getFormattedFilePath(activityType: ActivityType, cleaningObject: CleaningObject): String {
		val activityTypeString = activityType.toString()
			.replace(" ", "")
			.replace(".", "")
		val cleaningObjectString = cleaningObject.toSortString()
			.replace(" ", "")
			.replace(".", "")

		return "$TODOSFILEPATH-$cleaningObjectString-$activityTypeString.json"
	}

	fun readTodoList(activityType: ActivityType): MutableList<Todo> {
		return try {
			val text = File(getFormattedFilePath(activityType, cleaningObject)).readText()
			Json.decodeFromString<MutableList<Todo>>(text)
		} catch (_: IOException) {
			mutableListOf()
		}
	}

	fun saveTodoList(todoList: MutableList<Todo>) {
		makeDirectories()

		todoList.sortBy { it.date }

		val jsonText = Json.encodeToString(todoList)
		File(getFormattedFilePath(todoList.first().activity.activityType, cleaningObject)).writeText(jsonText)
	}
}
