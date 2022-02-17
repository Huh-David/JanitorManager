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
import de.davidhuh.janitormanager.domain.CleaningObject
import de.davidhuh.janitormanager.service.saveTodoList
import java.time.LocalDate

@Composable
fun todoOverviewRow(
	text1: String,
	onClick1: () -> Unit,
	text2: String,
	text3: String,
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
				Text(
					text = text1,
					modifier = Modifier
						.weight(3f)
						.wrapContentWidth(Alignment.Start)
				)
				Text(
					text = text2,
					modifier = Modifier
						.weight(2f)
						.wrapContentWidth(Alignment.End)
				)
			}
		}

		OutlinedButton(
			modifier = Modifier
				.weight(1f)
				.padding(end = 4.dp)
				.wrapContentWidth(Alignment.End),
			onClick = onClick2
		) {
			Row { Text(text3) }
		}
	}
}

@Composable
fun allTodosScreen(
	navController: NavController,
) {
	templateScreen {
		Text("All remaining Todos")
		val todoIndexMap = remember { mutableMapOf<Todo, Pair<CleaningObject, Int>>() }

		navController.cleaningObjectList.forEachIndexed { index, cleaningObject ->
			for (activityRepo in cleaningObject.activityRepoList) {
				activityRepo.getAllTodos(cleaningObject).forEach() { todo ->
					if (!todo.done) {
						todoIndexMap[todo] = Pair(cleaningObject, index)
					}
				}
			}
		}
		val sortedTodoIndexMap = todoIndexMap.toSortedMap(compareBy<Todo> { it.date }.thenBy { it.activity.toString() })

		sortedTodoIndexMap.forEach() { (todo, cleaningObjectIndexPair) ->
			val cleaningObjectText: MutableState<String> =
				remember { mutableStateOf("${cleaningObjectIndexPair.first}") }
			val activityText: MutableState<String> = remember { mutableStateOf("${todo.activity.activityType}") }
			val todoText: MutableState<String> = remember { mutableStateOf("$todo") }

			todoOverviewRow(
				text1 = cleaningObjectText.value,
				text2 = activityText.value,
				text3 = todoText.value,
				onClick1 = {
					navController.cleaningObjectIndex = cleaningObjectIndexPair.second
					navController.navigate(Screen.CleaningObjectScreen.name)
				},
				onClick2 = {
					// saveTodoList() TODO does not work
					todo.changeStatus()
					todoText.value = "$todo"
				},
			)
		}
	}
}
