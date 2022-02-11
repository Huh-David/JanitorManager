package de.davidhuh.janitormanager.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.davidhuh.janitormanager.ui.cards.activityCard
import de.davidhuh.janitormanager.ui.navcontroller.NavController

@Composable
fun CleaningObjectScreen(
	navController: NavController,
) {
	val cleaningObject = navController.cleaningObjectList.get(navController.cleaningObjectIndex)

	Column(
		modifier = Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text("[${navController.cleaningObjectIndex}] $cleaningObject")
		cleaningObject.activityList.forEach { activity ->
			activityCard(activity)
		}
		Button(
			onClick = {
				navController.navigate(Screen.HomeScreen.name)
			}) {
			Text("Back to Overview")
		}
	}
}
