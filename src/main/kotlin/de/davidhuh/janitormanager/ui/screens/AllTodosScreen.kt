package de.davidhuh.janitormanager.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.davidhuh.janitormanager.domain.Todo
import de.davidhuh.janitormanager.ui.navcontroller.NavController

@Composable
fun todoOverviewRow(
	text1: String,
	onClick1: () -> Unit,
	text2: String,
	onClick2: () -> Unit,
	modifier: Modifier = Modifier,
) {
	Row(modifier = modifier) {
		OutlinedButton(
			modifier = Modifier
				.weight(3f)
				.padding(start = 3.dp)
				.wrapContentWidth(Alignment.Start),
			onClick = onClick1
		) {
			Row {
				Text(text1)
			}
		}

		OutlinedButton(
			modifier = Modifier
				.weight(1f)
				.padding(end = 4.dp)
				.wrapContentWidth(Alignment.End),
			onClick = onClick2
		) {
			Row { Text(text2) }
		}
	}
}

@Composable
fun allTodosScreen(
	navController: NavController,
) {
	Column(modifier = Modifier.padding(start = 80.dp)) {
		navController.cleaningObjectList.forEachIndexed { index, cleaningObject ->
			cleaningObject.activityList.forEach { activity ->
				val todo: Todo
				if (activity.todoList.none() { !it.done }) {
					todo = activity.todoList.last()
				} else {
					todo = activity.todoList.first() { !it.done }
				}
				val text = "$cleaningObject ${activity.activityType}"
				val todoText: MutableState<String> = remember { mutableStateOf("$todo") }

				todoOverviewRow(
					text1 = text,
					text2 = todoText.value,
					onClick1 = {
						navController.cleaningObjectIndex = index
						navController.navigate(Screen.CleaningObjectScreen.name)
					},
					onClick2 = {
						todo.changeStatus()
						todoText.value = "$todo"
					},
				)
			}
		}
	}
}
