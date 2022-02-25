package de.davidhuh.janitormanager.adapter.service

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.*

object HelperService {
	fun getDirPath() = ".data/"
	fun getCleaningObjectFilePath() = "${getDirPath()}cleaningObjects.json"
	fun getTodosFilePath() = "${getDirPath()}todos"
}
