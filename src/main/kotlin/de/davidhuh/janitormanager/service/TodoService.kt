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

	fun readTodoList(activityType: ActivityType): MutableList<Todo> {
		return try {
			val activityTypeString = activityType.toString().replace(" ", "-")
			val cleaningObjectString = cleaningObject.toSortString().replace(" ", "-")

			val text = File("$TODOSFILEPATH-$cleaningObjectString-$activityTypeString.json").readText()
			Json.decodeFromString<MutableList<Todo>>(text)
		} catch (_: IOException) {
			mutableListOf()
		}
	}

	fun saveTodoList(todoList: MutableList<Todo>) {
		val activityTypeString = todoList.first().activity.activityType.toString().replace(" ", "-")
		val cleaningObjectString = cleaningObject.toSortString().replace(" ", "-")

		makeDirectories()

		todoList.sortBy { it.date }

		val jsonText = Json.encodeToString(todoList)
		File("$TODOSFILEPATH-$cleaningObjectString-$activityTypeString.json").writeText(jsonText)
	}
}
