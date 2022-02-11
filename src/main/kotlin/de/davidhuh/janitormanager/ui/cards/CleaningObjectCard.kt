package de.davidhuh.janitormanager.ui.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.davidhuh.janitormanager.domain.CleaningObject
import de.davidhuh.janitormanager.ui.navcontroller.NavController
import de.davidhuh.janitormanager.ui.screens.Screen

@Composable
fun cleaningObjectCard(
	cleaningObject: CleaningObject,
	navController: NavController,
) {


	OutlinedButton(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 10.dp),
		onClick = {
			navController.cleaningObject = cleaningObject
			navController.navigate(Screen.TestScreen.name)
		}
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
