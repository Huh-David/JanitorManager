package de.davidhuh.janitormanager.ui.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.davidhuh.janitormanager.domain.Activity
import de.davidhuh.janitormanager.domain.Todo
import de.davidhuh.janitormanager.ui.navcontroller.NavController
import de.davidhuh.janitormanager.ui.screens.Screen

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
				.padding(start = 4.dp)
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
fun activityCard(
	activity: Activity,
	index: Int,
	navController: NavController,
) {
	val firstTodo: Todo = if (activity.todoList.none { !it.done }) {
		remember { activity.todoList.last() }
//		activity.todoList.last()
	} else {
		remember { activity.todoList.first { !it.done } }
//		activity.todoList.first { !it.done }
	}

	val text: MutableState<String> = remember { mutableStateOf("$firstTodo") }

	todoOverviewRow(
		"${activity.activityType} ${text.value}",
		onClick1 = {
			firstTodo.changeStatus()
			text.value = "$firstTodo"
		},
		"Todo Overview",
		onClick2 = {
			navController.activityIndex = index
			navController.navigate(Screen.TodoOverviewScreen.name)
		},
		modifier = Modifier.padding(start = 80.dp)
	)
}
