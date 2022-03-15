package de.davidhuh.janitormanager.application.helper

object HelperService {
	fun getDirPath() = ".data/"
	fun getCleaningObjectFilePath() = "${getDirPath()}cleaningObjects.json"
	fun getTodosFilePath() = "${getDirPath()}todos"
}
