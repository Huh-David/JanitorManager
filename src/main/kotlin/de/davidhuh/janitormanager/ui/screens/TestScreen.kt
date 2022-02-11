package de.davidhuh.janitormanager.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.davidhuh.janitormanager.domain.CleaningObject
import de.davidhuh.janitormanager.ui.navcontroller.NavController

@Composable
fun TestScreen(
//	cleaningObject: CleaningObject,
	navController: NavController,
) {
	Column(
		modifier = Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(navController.cleaningObject.toString())
		Button(
			onClick = {
				navController.navigate(Screen.HomeScreen.name)
			}) {
			Text("Navigate to Notification")
		}
	}
}
