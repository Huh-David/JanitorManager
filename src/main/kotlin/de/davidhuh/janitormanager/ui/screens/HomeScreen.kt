package de.davidhuh.janitormanager.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.davidhuh.janitormanager.service.generateMockData
import de.davidhuh.janitormanager.ui.cards.cleaningObjectCard
import de.davidhuh.janitormanager.ui.navcontroller.NavController

@Composable
fun HomeScreen(
	navController: NavController,
) {
	val cleaningObjectList = generateMockData()

//	Column(
//		modifier = Modifier.fillMaxSize(),
//		verticalArrangement = Arrangement.Center,
//		horizontalAlignment = Alignment.CenterHorizontally
//	) {
//		Text(navController.currentScreen.value)
//		Button(
//			onClick = {
//				navController.navigate(Screen.TestScreen.name)
//			}) {
//			Text("Navigate to Profile")
//		}
//	}

	Column {
		cleaningObjectList.forEach { cleaningObject ->
			cleaningObjectCard(
				cleaningObject,
				navController
			)
		}
	}
}
