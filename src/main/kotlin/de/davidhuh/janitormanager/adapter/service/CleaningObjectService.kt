package de.davidhuh.janitormanager.adapter.service

import de.davidhuh.janitormanager.adapter.repository.ActivityAggregateRepo
import de.davidhuh.janitormanager.domain.valueobject.Address
import de.davidhuh.janitormanager.domain.entity.CleaningObject
import de.davidhuh.janitormanager.domain.entity.CleaningObjectManagement
import de.davidhuh.janitormanager.domain.entity.Todo
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
		makeDirectories(HelperService.getDirPath())

		val jsonText = Json.encodeToString(cleaningObjectList)
		File(HelperService.getCleaningObjectFilePath()).writeText(jsonText)
	}

	fun readCleaningObjectList(): MutableList<CleaningObject> {
		return try {
			val text = File(HelperService.getCleaningObjectFilePath()).readText()
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

		cleaningObject.activityAggregateList.forEach() {
			val activityAggreagteRepo = ActivityAggregateRepo(it, cleaningObject)
			val todoList = activityAggreagteRepo.getAllTodos()
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
