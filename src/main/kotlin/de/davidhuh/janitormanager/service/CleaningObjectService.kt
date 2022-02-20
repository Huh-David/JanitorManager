package de.davidhuh.janitormanager.service

import de.davidhuh.janitormanager.domain.Address
import de.davidhuh.janitormanager.domain.CleaningObject
import de.davidhuh.janitormanager.domain.CleaningObjectManagement
import de.davidhuh.janitormanager.domain.Todo
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException

class CleaningObjectService() {
	private fun makeDirectories(path: String) {
		if (!File(path).exists()) {
			File(path).mkdirs()
		}
	}

	fun saveCleaningObjectList(cleaningObjectList: MutableList<CleaningObject>) {
		makeDirectories(DIRPATH)

		val jsonText = Json.encodeToString(cleaningObjectList)
		File(CLEANINGOBJECTSFILEPATH).writeText(jsonText)
	}

	fun readCleaningObjectList(): MutableList<CleaningObject> {
		return try {
			val text = File(CLEANINGOBJECTSFILEPATH).readText()
			Json.decodeFromString<MutableList<CleaningObject>>(text)
		} catch (_: IOException) {
			mutableListOf()
		}
	}

	fun checkIfCleaningObjectsExist(): Boolean {
		try {
			if (readCleaningObjectList().size != 0) {
				return true
			}
		} catch (_: IOException) {
		}
		return false
	}

	fun changeAddressOfCleaningObject(
		cleaningObjectList: MutableList<CleaningObject>,
		cleaningObject: CleaningObject,
		newAddress: Address,
	) {
		val oldAddress = cleaningObject.address.copy()
		val todoListsToSave = mutableListOf<MutableList<Todo>>()

		cleaningObject.activityRepoList.forEach() {
			val todoList = it.getAllTodos(cleaningObject)
			todoListsToSave.add(todoList)
		}

		cleaningObject.changeAddress(newAddress)
		saveCleaningObjectList(cleaningObjectList)

		val todoService = TodoService(newAddress)

		for (todoListToSave in todoListsToSave) {
			todoService.changeAddressOfTodoList(oldAddress, todoListToSave)
		}
	}

	fun changeCleaningObjectManagementOfCleaningObject(
		cleaningObjectList: MutableList<CleaningObject>,
		cleaningObject: CleaningObject,
		newCleaningObjectManagement: CleaningObjectManagement,
	) {
		cleaningObject.changeCleaningObjectManagement(newCleaningObjectManagement)
		saveCleaningObjectList(cleaningObjectList)
	}
}
