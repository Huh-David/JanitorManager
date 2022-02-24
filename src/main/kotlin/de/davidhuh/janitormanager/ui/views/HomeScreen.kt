package de.davidhuh.janitormanager.ui.views

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import de.davidhuh.janitormanager.ui.cards.cleaningObjectCard
import de.davidhuh.janitormanager.ui.navcontroller.NavController

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
