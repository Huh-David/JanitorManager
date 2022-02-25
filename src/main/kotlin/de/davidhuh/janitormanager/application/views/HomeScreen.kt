package de.davidhuh.janitormanager.application.views

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import de.davidhuh.janitormanager.application.cards.cleaningObjectCard
import de.davidhuh.janitormanager.application.navcontroller.NavController

@Composable
fun homeScreen(
	navController: NavController,
) {
	templateScreen(
	) {
		Text("All Cleaning Objects")

		navController.cleaningObjectList.forEachIndexed { index, _ ->
			cleaningObjectCard(
				index,
				navController
			)
		}
	}
}
