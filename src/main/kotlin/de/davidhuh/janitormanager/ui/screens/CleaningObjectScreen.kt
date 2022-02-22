package de.davidhuh.janitormanager.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.davidhuh.janitormanager.domain.CleaningObject
import de.davidhuh.janitormanager.ui.cards.activityRepoCard
import de.davidhuh.janitormanager.ui.navcontroller.NavController

@Composable
fun generateActivityList(cleaningObject: CleaningObject, navController: NavController) {
	cleaningObject.activityAggregateList.forEachIndexed { index, activityRepo ->
		activityRepoCard(activityRepo, index, navController)
	}
}

@Composable
fun cleaningObjectScreen(
	navController: NavController,
) {
	val cleaningObject = navController.cleaningObjectList[navController.cleaningObjectIndex]

	templateScreen {
		Text(
			"[${navController.cleaningObjectIndex}] $cleaningObject",
			modifier = Modifier.padding(4.dp)
		)

		Column(
			modifier = Modifier
				.wrapContentWidth(Alignment.Start)
				.border(BorderStroke(2.dp, Color.Black))
				.padding(4.dp)
		) {
			Text("Cleaning Object Management", fontWeight = FontWeight.Bold)
			Text(cleaningObject.cleaningObjectManagement.generalInformation())
			Text(cleaningObject.cleaningObjectManagement.address.toString())
			Text(cleaningObject.cleaningObjectManagement.email, color = Color.Blue)
			Text(cleaningObject.cleaningObjectManagement.phone)
		}
		generateActivityList(cleaningObject, navController)
		Button(
			onClick = {
				// TODO implementation
			}
		) {
			Text("Update Overview (not implemented)")
		}
		Button(
			onClick = {
				navController.navigate(Screen.HomeScreen.name)
			}) {
			Text("Back to Overview")
		}
	}


}
