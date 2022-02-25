package de.davidhuh.janitormanager.adapter.service

object HelperService {
	fun getDirPath() = ".data/"
	fun getCleaningObjectFilePath() = "${getDirPath()}cleaningObjects.json"
	fun getTodosFilePath() = "${getDirPath()}todos"
}
