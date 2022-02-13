package de.davidhuh.janitormanager.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.davidhuh.janitormanager.ui.navcontroller.NavController

@Composable
fun todoOverviewScreen(
	navController: NavController,
) {

	Column(modifier = Modifier.padding(start = 80.dp)) {
		navController.cleaningObjectList[navController.cleaningObjectIndex].activityList[navController.activityIndex].todoList.forEachIndexed { _, todo ->

			val text: MutableState<String> = remember { mutableStateOf("$todo") }

			Button(
				modifier = Modifier.padding(4.dp),
				onClick = {
					todo.changeStatus()
					text.value = "$todo"
				}
			) {
				Text(text = text.value)
			}
			Divider()
		}
	}
}
