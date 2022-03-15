package de.davidhuh.janitormanager.adapter

import de.davidhuh.janitormanager.application.HelperService
import de.davidhuh.janitormanager.domain.entity.Todo
import de.davidhuh.janitormanager.domain.valueobject.ActivityType
import de.davidhuh.janitormanager.domain.valueobject.Address
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException

class TodoRepo(
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
