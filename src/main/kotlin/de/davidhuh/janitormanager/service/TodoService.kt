package de.davidhuh.janitormanager.service

import de.davidhuh.janitormanager.domain.ActivityType
import de.davidhuh.janitormanager.domain.Address
import de.davidhuh.janitormanager.domain.Todo
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException

class TodoService(
	private val address: Address,
) {
	private fun makeDirectories() {
		if (!File(HelperService.getDirPath()).exists()) {
			File(HelperService.getDirPath()).mkdirs()
		}
	}

	private fun getFormattedFilePath(activityType: ActivityType, address: Address): String {
		val activityTypeString = activityType.toString()
			.replace(" ", "")
			.replace(".", "")
		val addressString = address.toSortString()
			.replace(" ", "")
			.replace(".", "")

		return "${HelperService.getTodosFilePath()}-$addressString-$activityTypeString.json".lowercase()
	}

	fun changeAddressOfTodoList(oldAddress: Address, todoList: MutableList<Todo>) {
		saveTodoList(todoList)
		removeTodoList(oldAddress, todoList.first().activity.activityType)
	}

	fun removeTodoList(address: Address, activityType: ActivityType) {
		File(getFormattedFilePath(activityType, address)).delete()
	}

	fun readTodoList(activityType: ActivityType): MutableList<Todo> {
		return try {
			val text = File(getFormattedFilePath(activityType, address)).readText()
			Json.decodeFromString<MutableList<Todo>>(text)
		} catch (_: IOException) {
			mutableListOf()
		}
	}

	fun saveTodoList(todoList: MutableList<Todo>) {
		makeDirectories()

		todoList.sortBy { it.date }

		val jsonText = Json.encodeToString(todoList)
		File(getFormattedFilePath(todoList.first().activity.activityType, address)).writeText(jsonText)
	}
}
