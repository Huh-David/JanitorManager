package de.davidhuh.janitormanager.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.davidhuh.janitormanager.domain.Todo
import de.davidhuh.janitormanager.service.TodoService
import de.davidhuh.janitormanager.ui.navcontroller.NavController

@Composable
fun todoOverviewScreen(
	navController: NavController,
) {
	templateScreen {
		val cleaningObject = navController.cleaningObjectList[navController.cleaningObjectIndex]

		Text("[${navController.cleaningObjectIndex}] $cleaningObject")
		Column() {
			val activityIndex = navController.activityIndex
			val todoList: MutableList<Todo> = cleaningObject.activityAggregateList[activityIndex].getAllTodos(cleaningObject)
			val todoService = TodoService(cleaningObject.address)

			todoList.forEachIndexed { index, todo ->

				val activityText = todo.activity.activityType.toString()
				val text: MutableState<String> = remember { mutableStateOf("$todo") }

				Button(
					modifier = Modifier.padding(4.dp),
					onClick = {
						todo.changeStatus()
						todoService.saveTodoList(todoList)
						text.value = "$todo"
					}
				) {
					Text(
						text = activityText,
						modifier = Modifier
							.wrapContentWidth(align = Alignment.Start)
							.weight(3f)
					)
					Text(
						text = text.value,
						modifier = Modifier
							.wrapContentWidth(align = Alignment.End)
							.weight(3f)
					)
				}
				if (index < todoList.size - 1) {
					Divider()
				}
			}
		}
	}
}

