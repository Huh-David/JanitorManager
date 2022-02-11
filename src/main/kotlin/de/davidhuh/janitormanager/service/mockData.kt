package de.davidhuh.janitormanager.service

import de.davidhuh.janitormanager.domain.*
import java.time.LocalDate
import kotlin.random.Random

fun generateMockData(): MutableList<CleaningObject> {
	val cleaningObjectList = mutableListOf<CleaningObject>()

	val preNameList = mutableListOf<String>("Nico", "Patrick", "Lisa", "OtherCoolDudesPreNames", "David")
	val surNameList = mutableListOf<String>("Holzi", "Mueller", "Klitschko", "OtherCoolDudesSurNames", "Huh")
	val activityTypeNames = mutableListOf<String>("Clean stairs", "Cut the lawn", "Bring out garbage")
	val sectorNames = mutableListOf<String>("Indoor", "Outdoor")
	val cityNames = mutableListOf<String>("Karlsruhe", "Bühl", "Sinsheim", "Stuttgart", "Offenburg")
	val streetNames = mutableListOf<String>("Hauptstr.", "Bahnhofsstr.", "Schulstr.", "Cool street")

	for (i in 1..10) {
		var activityList = mutableListOf<Activity>()

		val preName = preNameList.random()
		val surName = surNameList.random()
		val email = "mail@$preName$surName.de"
		val phone = Random.nextInt(100000, 999999).toString()

		val address =
			Address(streetNames.random(),
				Random.nextInt(1, 100).toString(),
				Random.nextInt(10000, 99999).toString(),
				cityNames.random())
		val cleaningObjectManagement = CleaningObjectManagement(preName, surName, phone, email, address)
		val startDate = LocalDate.of(Random.nextInt(2020, 2022), Random.nextInt(1, 12), Random.nextInt(1, 28))

		for (j in 1..Random.nextInt(1, 10)) {
			val sectorName = sectorNames.random()
			val activityTypeName = activityTypeNames.random()

			val sector = Sector(sectorName)
			val activityType = ActivityType(activityTypeName, sector)
			val activity = Activity(startDate, Random.nextInt(1, 365), activityType)

			activityList.add(activity)
		}

		activityList = activityList.sortedBy { it.toString() } as MutableList<Activity>

		val cleaningObject = CleaningObject(address, cleaningObjectManagement, activityList)

		cleaningObjectList.add(cleaningObject)
	}

	return cleaningObjectList
}
