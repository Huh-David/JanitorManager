package de.davidhuh.janitormanager.application

object HelperService {
	fun getDirPath() = ".data/"
	fun getCleaningObjectFilePath() = "${getDirPath()}cleaningObjects.json"
	fun getTodosFilePath() = "${getDirPath()}todos"
}
