package de.davidhuh.janitormanager.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import de.davidhuh.janitormanager.domain.CleaningObject
import de.davidhuh.janitormanager.domain.Todo
import de.davidhuh.janitormanager.service.TodoService
import de.davidhuh.janitormanager.ui.navcontroller.NavController

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
				.weight(2f)
				.padding(start = 3.dp, top = 4.dp, bottom = 4.dp)
				.wrapContentWidth(Alignment.Start)
				.height(50.dp),
			onClick = onClick1
		) {
			Row(
				modifier = Modifier
			) {
				Text(
					text = text1,
					modifier = Modifier
						.weight(1f)
						.wrapContentWidth(Alignment.Start)
				)
				Text(
					text = text2,
					modifier = Modifier
						.weight(1f)
						.wrapContentWidth(Alignment.End),
					textAlign = TextAlign.End
				)
			}
		}

		OutlinedButton(
			modifier = Modifier
				.weight(2f)
				.padding(end = 4.dp, top = 4.dp, bottom = 4.dp)
				.wrapContentWidth(Alignment.End)
				.height(50.dp),
			onClick = onClick2
		) {
			Row {
				Text(text3)
			}
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
					if (!todo.isDone()) {
						todoIndexMap[todo] = Pair(cleaningObject, index)
					}
				}
			}
		}
		val sortedTodoIndexMap = todoIndexMap.toSortedMap(compareBy<Todo> { it.date }.thenBy { it.activity.toString() })

		sortedTodoIndexMap.forEach() { (todo, cleaningObjectIndexPair) ->
			val cleaningObjectText: MutableState<String> = remember {
				mutableStateOf(cleaningObjectIndexPair.first.address.toStringTwoLines())
			}
			val activityText: MutableState<String> =
				remember { mutableStateOf(todo.activity.activityType.toStringTwoLines()) }
			val todoText: MutableState<String> = remember {
				mutableStateOf(todo.toStringWithOverdueDays())
			}

			todoOverviewRow(
				text1 = cleaningObjectText.value,
				text2 = activityText.value,
				text3 = todoText.value,
				onClick1 = {
					navController.cleaningObjectIndex = cleaningObjectIndexPair.second
					navController.navigate(Screen.CleaningObjectScreen.name)
				},
				onClick2 = {
					val cleaningObject = cleaningObjectIndexPair.first
					val todoService = TodoService(cleaningObject.address)
					val todoList = cleaningObject.activityRepoList.find {
						it.activityType == todo.activity.activityType
					}?.getAllTodos(cleaningObject)


					if (todoList != null) {
						todoList.find { it == todo }?.changeStatus()
						todoService.saveTodoList(todoList)
					}

					todoText.value = "$todo"
				},
			)
		}
	}
}
