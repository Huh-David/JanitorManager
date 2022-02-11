// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
	val cityNames = mutableListOf<String>("Karlsruhe", "Bühl", "Sinsheim", "Stuttgart", "Offenburg")
	val streetNames = mutableListOf<String>("Hauptstr.", "Bahnhofsstr.", "Schulstr.", "Cool street")

	for (i in 1..10) {
		val activityList = mutableListOf<Activity>()

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

		val cleaningObject = CleaningObject(address, cleaningObjectManagement, activityList)

		cleaningObjectList.add(cleaningObject)
	}

	return cleaningObjectList
}

@Composable
fun activityCard(
	activity: Activity,
) {
	OutlinedButton(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 10.dp),
		onClick = { /* Do something! */ }
	) {
		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			Text("${activity.activityType}")
			Text("${activity.startDate}")
		}
	}
}

@Composable
fun cleaningObjectCard(
	cleaningObject: CleaningObject,
) {
	OutlinedButton(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 10.dp),
		onClick = {/**/ }
	) {
		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			Text("$cleaningObject")
			Text("${cleaningObject.activityList.size} Activities")
		}
	}
}

@Composable
@Preview
fun App() {
	val cleaningObjectList = generateMockData()

	MaterialTheme {
		Column {
			cleaningObjectList.forEach { cleaningObject ->
				cleaningObjectCard(cleaningObject)
			}
		}
	}
}

fun main() = application {
	Window(onCloseRequest = ::exitApplication) {
		App()
	}
}
