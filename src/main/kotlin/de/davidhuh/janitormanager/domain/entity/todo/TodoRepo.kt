package de.davidhuh.janitormanager.domain.entity.todo

import de.davidhuh.janitormanager.domain.entity.activity.ActivityType
import de.davidhuh.janitormanager.domain.valueobject.address.Address

interface TodoRepo {
	fun makeDirectories()

	fun getFormattedFilePath(activityType: ActivityType, address: Address): String

	fun changeAddressOfTodoList(oldAddress: Address, todoList: MutableList<Todo>)

	fun removeTodoList(address: Address, activityType: ActivityType)

	fun readTodoList(activityType: ActivityType): MutableList<Todo>

	fun saveTodoList(todoList: MutableList<Todo>)
}
