package de.davidhuh.janitormanager.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.davidhuh.janitormanager.ui.cards.cleaningObjectCard
import de.davidhuh.janitormanager.ui.navcontroller.NavController

@Composable
fun homeScreen(
	navController: NavController,
) {

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

	Column(modifier = Modifier.padding(start = 80.dp)) {
		navController.cleaningObjectList.forEachIndexed { index, _ ->
			cleaningObjectCard(
				index,
				navController
			)
		}
	}
}
