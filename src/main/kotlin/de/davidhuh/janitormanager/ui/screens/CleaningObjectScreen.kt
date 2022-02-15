package de.davidhuh.janitormanager.ui.screens

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.davidhuh.janitormanager.domain.CleaningObject
import de.davidhuh.janitormanager.ui.cards.activityRepoCard
import de.davidhuh.janitormanager.ui.navcontroller.NavController

@Composable
fun generateActivityList(cleaningObject: CleaningObject, navController: NavController) {
	return cleaningObject.activityRepoList.forEachIndexed { index, activityRepo ->
		activityRepoCard(activityRepo, index, navController)
//		activityRepo.activityList.forEach { activity ->
//			activityCard(activity, index, navController)
//		}
	}
}

@Composable
fun cleaningObjectScreen(
	navController: NavController,
) {
	val stateVertical = rememberScrollState(0)

	VerticalScrollbar(
		modifier = Modifier
			.fillMaxHeight(),
		adapter = rememberScrollbarAdapter(stateVertical),
	)

	val cleaningObject = navController.cleaningObjectList[navController.cleaningObjectIndex]

	Column(
		modifier = Modifier
			.padding(start = 80.dp) // This line is important
			.fillMaxSize()
			.verticalScroll(stateVertical),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text("[${navController.cleaningObjectIndex}] $cleaningObject")
		generateActivityList(cleaningObject, navController)
		Button(
			onClick = {
				// TODO implementation
			}
		) {
			Text("Update Overview")
		}
		Button(
			onClick = {
				navController.navigate(Screen.HomeScreen.name)
			}) {
			Text("Back to Overview")
		}
	}
}
