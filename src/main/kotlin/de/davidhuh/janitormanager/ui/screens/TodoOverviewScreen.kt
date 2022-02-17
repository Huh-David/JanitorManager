package de.davidhuh.janitormanager.ui.screens

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
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
import de.davidhuh.janitormanager.service.saveTodoList
import de.davidhuh.janitormanager.ui.navcontroller.NavController
import org.intellij.lang.annotations.JdkConstants.CalendarMonth

@Composable
fun todoOverviewScreen(
	navController: NavController,
) {
	templateScreen {
		val cleaningObject = navController.cleaningObjectList[navController.cleaningObjectIndex]

		Text("[${navController.cleaningObjectIndex}] $cleaningObject")
		Column() {
			val activityIndex = navController.activityIndex
			val todoList: MutableList<Todo> = cleaningObject.activityRepoList[activityIndex].getAllTodos(cleaningObject)

			todoList.forEachIndexed { index, todo ->

				val activityText = todo.activity.activityType.toString()
				val text: MutableState<String> = remember { mutableStateOf("$todo") }

				Button(
					modifier = Modifier.padding(4.dp),
					onClick = {
						todo.changeStatus()
						saveTodoList(todoList, cleaningObject)
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

