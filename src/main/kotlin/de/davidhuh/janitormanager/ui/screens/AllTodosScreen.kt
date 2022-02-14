package de.davidhuh.janitormanager.ui.screens

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.foundation.HorizontalScrollbar
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication

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
	val stateVertical = rememberScrollState(0)


	VerticalScrollbar(
		modifier = Modifier
			.fillMaxHeight(),
		adapter = rememberScrollbarAdapter(stateVertical)
	)

	Column(
		modifier = Modifier
			.padding(start = 80.dp)
			.verticalScroll(stateVertical)
	) {


		navController.cleaningObjectList.forEachIndexed { index, cleaningObject ->
			for (activityRepo in cleaningObject.activityRepoList) {
				val todo: Todo = activityRepo.getFirstTodo()
				val text = "$cleaningObject ${activityRepo.activityType}"
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

						if (activityRepo.getAllTodos().none() { !it.done }) {
							todoText.value = "Everything Done"
						} else {
							todoText.value = "${activityRepo.getFirstTodo()}"
						}

					},
				)
			}
		}
//		navController.cleaningObjectList.forEachIndexed { index, cleaningObject ->
//			for (activity in cleaningObject.activityList) {
//				val todo: Todo = if (activity.todoList.none() { !it.done }) {
//					continue
//				} else {
//					activity.todoList.first() { !it.done }
//				}
//				val text = "$cleaningObject ${activity.activityType}"
//				val todoText: MutableState<String> = remember { mutableStateOf("$todo") }
//
//				todoOverviewRow(
//					text1 = text,
//					text2 = todoText.value,
//					onClick1 = {
//						navController.cleaningObjectIndex = index
//						navController.navigate(Screen.CleaningObjectScreen.name)
//					},
//					onClick2 = {
//						todo.changeStatus()
//
//						if (activity.todoList.none() { !it.done }) {
//							todoText.value = "Everything Done"
//						} else {
//							todoText.value = "${activity.todoList.first() { !it.done }}"
//						}
//
//					},
//				)
//			}
//		}
	}
}
