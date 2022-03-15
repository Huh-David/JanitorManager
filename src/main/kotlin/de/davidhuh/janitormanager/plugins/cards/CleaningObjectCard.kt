package de.davidhuh.janitormanager.plugins.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.davidhuh.janitormanager.plugins.navcontroller.NavController
import de.davidhuh.janitormanager.plugins.views.Screen

@Composable
fun cleaningObjectCard(
	cleaningObjectIndex: Int,
	navController: NavController,
) {
	val cleaningObject = navController.cleaningObjectList[cleaningObjectIndex]

	OutlinedButton(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 10.dp),
		onClick = {
			navController.cleaningObjectIndex = cleaningObjectIndex
			navController.navigate(Screen.CleaningObjectScreen.name)
		}
	) {
		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			Text("[$cleaningObjectIndex] ${cleaningObject.toAlternativeString()}")
			Text("${cleaningObject.activityAggregateList.size} Activities")
		}
	}
}