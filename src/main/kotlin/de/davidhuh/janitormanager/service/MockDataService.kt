package de.davidhuh.janitormanager.service

import de.davidhuh.janitormanager.domain.*
import de.davidhuh.janitormanager.repository.ActivityRepo
import kotlinx.datetime.LocalDate
import kotlin.random.Random

const val DIRPATH = ".data/"
const val CLEANINGOBJECTSFILEPATH = "${DIRPATH}cleaningObjects.json"
const val TODOSFILEPATH = "${DIRPATH}todos"

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
		val activityTypeNames = mutableListOf<String>("Clean stairs", "Cut the lawn", "Bring out garbage")

		val activityRepoList = mutableListOf<ActivityRepo>()
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
			for (activityRepo in activityRepoList) {
				if (activityRepo.addActivity(activity)) {
					added = true
				}
			}
			if (!added) {
				val activityRepo = ActivityRepo(activityType, mutableListOf(activity))
				activityRepoList.add(activityRepo)
			}
		}

		return CleaningObject(
			cleaningObjectAddress,
			cleaningObjectManagement,
			activityRepoList,
			cleaningObjectType
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
