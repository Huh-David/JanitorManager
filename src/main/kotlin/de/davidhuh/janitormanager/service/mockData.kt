package de.davidhuh.janitormanager.service

import de.davidhuh.janitormanager.domain.*
import de.davidhuh.janitormanager.repository.ActivityRepo
import java.time.LocalDate
import kotlin.random.Random

fun generateMockData(): MutableList<CleaningObject> {
	val cleaningObjectList = mutableListOf<CleaningObject>()

	val preNameList = mutableListOf<String>("Nico", "Patrick", "Lisa", "OtherCoolDudesPreNames", "David")
	val surNameList = mutableListOf<String>("Holzi", "Mueller", "Klitschko", "OtherCoolDudesSurNames", "Huh")
	val activityTypeNames = mutableListOf<String>("Clean stairs", "Cut the lawn", "Bring out garbage")
	val sectorNames = mutableListOf<String>("Indoor")//, "Outdoor", "Somewhere")
	val cityNames = mutableListOf<String>("Karlsruhe", "Bühl", "Sinsheim", "Sinzheim", "Stuttgart", "Offenburg")
	val streetNames = mutableListOf<String>("Hauptstr.", "Bahnhofsstr.", "Schulstr.", "Cool street")

	for (i in 1..10) {
		var activityList = mutableListOf<Activity>()
		val activityRepoList = mutableListOf<ActivityRepo>()

		val preName = preNameList.random()
		val surName = surNameList.random()
		val email = mutableListOf<String>("mail@$preName$surName.de",
			"$preName.$surName@notgmail.de",
			"$preName.$surName@dataseller.de").random()
		val phone = Random.nextInt(100000, 999999).toString()

		val address =
			Address(streetNames.random(),
				Random.nextInt(1, 100).toString(),
				Random.nextInt(10000, 99999).toString(),
				cityNames.random())
		val cleaningObjectManagement = CleaningObjectManagement(preName, surName, phone, email, address)

		for (j in 1..Random.nextInt(1, 10)) {
			val startDate = LocalDate.of(Random.nextInt(2020, 2022), Random.nextInt(1, 12), Random.nextInt(1, 28))
			val sectorName = sectorNames.random()
			val activityTypeName = activityTypeNames.random()

			val sector = Sector(sectorName)
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

			activityList.add(activity)
		}

		activityList = activityList.sortedBy { it.toString() } as MutableList<Activity>

		val cleaningObject = CleaningObject(address, cleaningObjectManagement, activityList, activityRepoList)

		cleaningObjectList.add(cleaningObject)
	}

	return cleaningObjectList
}
