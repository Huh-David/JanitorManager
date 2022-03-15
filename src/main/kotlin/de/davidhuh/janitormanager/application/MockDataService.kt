package de.davidhuh.janitormanager.application

import de.davidhuh.janitormanager.domain.entity.*
import de.davidhuh.janitormanager.domain.entity.aggregate.ActivityAggregate
import de.davidhuh.janitormanager.domain.valueobject.ActivityType
import de.davidhuh.janitormanager.domain.valueobject.Address
import de.davidhuh.janitormanager.domain.valueobject.CleaningObjectType
import de.davidhuh.janitormanager.domain.valueobject.Sector
import kotlinx.datetime.LocalDate
import java.util.*
import kotlin.random.Random

class MockDataService() {
	private fun generateAddress(): Address {
		val cityNames = mutableListOf<String>("Karlsruhe", "Bühl", "Sinsheim", "Sinzheim", "Stuttgart", "Offenburg")
		val streetNames = mutableListOf<String>("Hauptstr.", "Bahnhofsstr.", "Schulstr.", "Cool street")

		return Address(streetNames.random(),
			Random.nextInt(1, 100).toString(),
			Random.nextInt(10000, 99999).toString(),
			cityNames.random())
	}

	private fun generateName(): Pair<String, String> {
		val preNameList = mutableListOf<String>("Nico", "Patrick", "Lisa", "Prename", "David")
		val surNameList = mutableListOf<String>("Holzi", "Mueller", "Klitschko", "Surname", "Huh")

		val preName = preNameList.random()
		val surName = surNameList.random()

		return Pair(preName, surName)
	}

	private fun generateCleaningObjectManagement(): CleaningObjectManagement {
		val name = generateName()
		val preName = name.first
		val surName = name.second

		val possibleEmails = mutableListOf<String>(
			"mail@$preName$surName.de",
			"$preName.$surName@notgmail.de",
			"$preName.$surName@dataseller.de"
		)

		val email = possibleEmails.random()
		val phone = Random.nextInt(100000, 999999).toString()
		val address = generateAddress()

		return CleaningObjectManagement(preName, surName, phone, email, address)
	}

	private fun generateRandomDate(): LocalDate {
		return LocalDate(Random.nextInt(2021, 2022), Random.nextInt(7, 12), Random.nextInt(1, 28))
	}

	private fun generateCleaningObject(): CleaningObject {
		val activityTypeNames = mutableListOf<String>(
			"Clean stairs",
			"Cut the lawn",
			"Bring out garbage"
		)

		val activityAggregateList = mutableListOf<ActivityAggregate>()
		val cleaningObjectType = CleaningObjectType.values().random()
		val cleaningObjectAddress = generateAddress()
		val cleaningObjectManagement = generateCleaningObjectManagement()

		for (j in 1..Random.nextInt(1, 10)) {
			val startDate = generateRandomDate()
			val activityTypeName = activityTypeNames.random()

			val sector = Sector.values().random()
			val activityType = ActivityType(activityTypeName, sector)
			val activity = Activity(startDate, Random.nextInt(1, 365), activityType)

			var added = false
			for (activityRepo in activityAggregateList) {
				if (activityRepo.addActivity(activity)) {
					added = true
				}
			}
			if (!added) {
				val activityAggregate = ActivityAggregate(activityType, mutableListOf(activity))
				activityAggregateList.add(activityAggregate)
			}
		}

		return CleaningObject(
			cleaningObjectAddress,
			cleaningObjectManagement,
			activityAggregateList,
			cleaningObjectType
		)
	}

	fun generateEmployee(): Employee {
		return if (Random.nextBoolean()) {
			generateGardener()
		} else {
			generateCleaningSpecialist()
		}
	}

	private fun generateGardener(): Gardener {
		val fullName = generateName()

		return Gardener(
			employeeId = UUID.randomUUID(),
			preName = fullName.first,
			surName = fullName.second,
			registrationDate = generateRandomDate()
		)
	}

	private fun generateCleaningSpecialist(): CleaningSpecialist {
		val fullName = generateName()

		return CleaningSpecialist(
			employeeId = UUID.randomUUID(),
			preName = fullName.first,
			surName = fullName.second,
			registrationDate = generateRandomDate()
		)
	}

	fun generateCleaningObjects(amount: Int): MutableList<CleaningObject> {
		val cleaningObjectList = mutableListOf<CleaningObject>()

		for (i in 1..amount) {
			cleaningObjectList.add(generateCleaningObject())
		}

		return cleaningObjectList
	}

}
