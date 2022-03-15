package de.davidhuh.janitormanager.plugins.persistence.todo

import de.davidhuh.janitormanager.application.HelperService
import de.davidhuh.janitormanager.domain.entity.activity.ActivityType
import de.davidhuh.janitormanager.domain.entity.todo.Todo
import de.davidhuh.janitormanager.domain.entity.todo.TodoRepo
import de.davidhuh.janitormanager.domain.valueobject.address.Address
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException

class TodoPersistenceRepo(
	private val address: Address,
) : TodoRepo {
	override fun makeDirectories() {
		if (!File(HelperService.getDirPath()).exists()) {
			File(HelperService.getDirPath()).mkdirs()
		}
	}

	override fun getFormattedFilePath(activityType: ActivityType, address: Address): String {
		val activityTypeString = activityType.toString()
			.replace(" ", "")
			.replace(".", "")
		val addressString = address.toSortString()
			.replace(" ", "")
			.replace(".", "")

		return "${HelperService.getTodosFilePath()}-$addressString-$activityTypeString.json".lowercase()
	}

	override fun changeAddressOfTodoList(oldAddress: Address, todoList: MutableList<Todo>) {
		saveTodoList(todoList)
		removeTodoList(oldAddress, todoList.first().activity.activityType)
	}

	override fun removeTodoList(address: Address, activityType: ActivityType) {
		File(getFormattedFilePath(activityType, address)).delete()
	}

	override fun readTodoList(activityType: ActivityType): MutableList<Todo> {
		return try {
			val text = File(getFormattedFilePath(activityType, address)).readText()
			Json.decodeFromString<MutableList<Todo>>(text)
		} catch (_: IOException) {
			mutableListOf()
		}
	}

	override fun saveTodoList(todoList: MutableList<Todo>) {
		makeDirectories()

		todoList.sortBy { it.date }

		val jsonText = Json.encodeToString(todoList)
		File(getFormattedFilePath(todoList.first().activity.activityType, address)).writeText(jsonText)
	}
}
