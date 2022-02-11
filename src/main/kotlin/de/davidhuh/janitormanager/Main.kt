// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import de.davidhuh.janitormanager.domain.*
import java.time.LocalDate
import kotlin.random.Random

fun generateMockData(): MutableList<CleaningObject> {
	val cleaningObjectList = mutableListOf<CleaningObject>()

	val preNameList = mutableListOf<String>("Nico", "Patrick", "Lisa", "OtherCoolDudesPreNames", "David")
	val surNameList = mutableListOf<String>("Holzi", "Mueller", "Klitschko", "OtherCoolDudesSurNames", "Huh")
	val activityTypeNames = mutableListOf<String>("Clean stairs", "Cut the lawn", "Bring out garbage")
	val sectorNames = mutableListOf<String>("Indoor", "Outdoor")

	for (i in 1..10) {
		val preName = preNameList.random()
		val surName = surNameList.random()
		val email = "mail@$preName$surName.de"
		val phone = Random.nextInt(100000, 999999).toString()
		val sectorName = sectorNames.random()
		val activityTypeName = activityTypeNames.random()

		val address =
			Address("Str.", Random.nextInt(1, 100).toString(), Random.nextInt(10000, 99999).toString(), "City")
		val cleaningObjectManagement = CleaningObjectManagement(preName, surName, phone, email, address)
		val sector = Sector(sectorName)
		val activityType = ActivityType(activityTypeName, sector)
		val startDate = LocalDate.of(Random.nextInt(2020, 2022), Random.nextInt(1, 12), Random.nextInt(1, 28))
		val activityOne = Activity(startDate, Random.nextInt(1, 365), activityType)
		val activityList = mutableListOf<Activity>(activityOne)
		val cleaningObject = CleaningObject(address, cleaningObjectManagement, activityList)

		cleaningObjectList.add(cleaningObject)
	}

	return cleaningObjectList
}

@Composable
@Preview
fun App() {
	val cleaningObjectList = generateMockData()


//	var cleaningObjectAddress by remember { mutableStateOf(cleaningObject.address.toString()) }

	MaterialTheme {
		Column {
			for (cleaningObject in cleaningObjectList) {
				var cleaningObjectAddress by remember { mutableStateOf(cleaningObject.address.toString()) }

				Button(onClick = {
					cleaningObjectAddress = "Clicked Address!"
				}) {
					Text(cleaningObjectAddress)
				}
			}
		}
	}
}

fun main() = application {
	Window(onCloseRequest = ::exitApplication) {
		App()
	}
}
