package de.davidhuh.janitormanager.domain.entity.cleaningobject

import de.davidhuh.janitormanager.domain.entity.cleaningobjectmanagement.CleaningObjectManagement
import de.davidhuh.janitormanager.domain.valueobject.address.Address

interface CleaningObjectRepo {
	fun makeDirectories(path: String)

	fun saveCleaningObjectList(cleaningObjectList: MutableList<CleaningObject>)

	fun readCleaningObjectList(): MutableList<CleaningObject>

	fun checkIfCleaningObjectsExist(): Boolean

	fun changeAddressOfCleaningObject(
		cleaningObjectList: MutableList<CleaningObject>,
		cleaningObject: CleaningObject,
		newAddress: Address,
	)

	fun changeCleaningObjectManagementOfCleaningObject(
		cleaningObjectList: MutableList<CleaningObject>,
		cleaningObject: CleaningObject,
		newCleaningObjectManagement: CleaningObjectManagement,
	)
}
