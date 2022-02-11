// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
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

fun generateMockData(): CleaningObject {
	val address = Address("Str.", "123", "12345", "City")
	val cleaningObjectManagement = CleaningObjectManagement("David", "Huh", "12345", "mail@davidhuh.de", address)
	val sector = Sector("Indoor")
	val activityType = ActivityType("Clean stairs", sector)
	val startDate = LocalDate.of(2022, 1, 1)
	val activityOne = Activity(startDate, 7, activityType)
	val activityList = mutableListOf<Activity>(activityOne)
	val cleaningObject = CleaningObject(address, cleaningObjectManagement, activityList)

	return cleaningObject
}

@Composable
@Preview
fun App() {
	val cleaningObject = generateMockData()
	var cleaningObjectAddress by remember { mutableStateOf(cleaningObject.address.toString()) }

	MaterialTheme {
		Button(onClick = {
			cleaningObjectAddress = "Copied Address!"
		}) {
			Text(cleaningObjectAddress)
		}
	}
}

fun main() = application {
	Window(onCloseRequest = ::exitApplication) {
		App()
	}
}
