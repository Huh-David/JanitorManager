package de.davidhuh.janitormanager.domain

import kotlinx.datetime.LocalDate
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.UUID

object UUIDSerializer : KSerializer<UUID> {
	override val descriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

	override fun deserialize(decoder: Decoder): UUID {
		return UUID.fromString(decoder.decodeString())
	}

	override fun serialize(encoder: Encoder, value: UUID) {
		encoder.encodeString(value.toString())
	}
}

@Serializable
class Employee(
	@Serializable(with = UUIDSerializer::class)
	val employeeId: UUID,
	val preName: String,
	val surName: String,
	val registrationDate: LocalDate
) {
}
