package de.davidhuh.janitormanager.plugins.persistence.cleaningobject

import de.davidhuh.janitormanager.application.helper.HelperService
import de.davidhuh.janitormanager.application.activity.ActivityAggregateService
import de.davidhuh.janitormanager.domain.entity.cleaningobject.CleaningObject
import de.davidhuh.janitormanager.domain.entity.cleaningobject.CleaningObjectRepo
import de.davidhuh.janitormanager.domain.entity.cleaningobjectmanagement.CleaningObjectManagement
import de.davidhuh.janitormanager.domain.entity.todo.Todo
import de.davidhuh.janitormanager.domain.valueobject.address.Address
import de.davidhuh.janitormanager.plugins.persistence.todo.TodoPersistenceRepo
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException

class CleaningObjectPersistenceRepo() : CleaningObjectRepo {
	override fun makeDirectories(path: String) {
		if (!File(path).exists()) {
			File(path).mkdirs()
		}
	}

	override fun saveCleaningObjectList(cleaningObjectList: MutableList<CleaningObject>) {
		makeDirectories(HelperService.getDirPath())

		val jsonText = Json.encodeToString(cleaningObjectList)
		File(HelperService.getCleaningObjectFilePath()).writeText(jsonText)
	}

	override fun readCleaningObjectList(): MutableList<CleaningObject> {
		return try {
			val text = File(HelperService.getCleaningObjectFilePath()).readText()
			Json.decodeFromString<MutableList<CleaningObject>>(text)
		} catch (_: IOException) {
			mutableListOf()
		}
	}

	override fun checkIfCleaningObjectsExist(): Boolean {
		try {
			if (readCleaningObjectList().size != 0) {
				return true
			}
		} catch (_: IOException) {
		}
		return false
	}

	override fun changeAddressOfCleaningObject(
		cleaningObjectList: MutableList<CleaningObject>,
		cleaningObject: CleaningObject,
		newAddress: Address,
	) {
		val oldAddress = cleaningObject.address.copy()
		val todoListsToSave = mutableListOf<MutableList<Todo>>()

		cleaningObject.activityAggregateList.forEach() {
			val activityAggregateService = ActivityAggregateService(it, cleaningObject)
			val todoPersistenceRepo = TodoPersistenceRepo(cleaningObject.address)
			val todoList = activityAggregateService.getAllTodos(todoPersistenceRepo)

			todoListsToSave.add(todoList)
		}

		cleaningObject.changeAddress(newAddress)
		saveCleaningObjectList(cleaningObjectList)

		val todoPersistenceRepo = TodoPersistenceRepo(cleaningObject.address)

		for (todoListToSave in todoListsToSave) {
			todoPersistenceRepo.changeAddressOfTodoList(oldAddress, todoListToSave)
		}
	}

	override fun changeCleaningObjectManagementOfCleaningObject(
		cleaningObjectList: MutableList<CleaningObject>,
		cleaningObject: CleaningObject,
		newCleaningObjectManagement: CleaningObjectManagement,
	) {
		cleaningObject.changeCleaningObjectManagement(newCleaningObjectManagement)
		saveCleaningObjectList(cleaningObjectList)
	}
}
