package de.davidhuh.janitormanager.application.cards

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
import de.davidhuh.janitormanager.domain.entity.Todo
import de.davidhuh.janitormanager.domain.entity.aggregate.ActivityAggregate
import de.davidhuh.janitormanager.adapter.service.TodoService
import de.davidhuh.janitormanager.application.navcontroller.NavController
import de.davidhuh.janitormanager.application.views.Screen

@Composable
fun todoOverviewRow(
	text1: String,
	text2: String,
	onClick1: () -> Unit,
	text3: String,
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
			Row(
				modifier = Modifier
					.wrapContentWidth(Alignment.Start)
					.weight(5f)
			) {
				Text(text1)
			}
			Row(
				modifier = Modifier
					.wrapContentWidth(Alignment.End)
					.weight(2f)
			) {
				Text(text2)
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
fun activityRepoCard(
	activityAggregate: ActivityAggregate,
	index: Int,
	navController: NavController,
) {
	val cleaningObject = navController.cleaningObjectList[navController.cleaningObjectIndex]
	val todoList = activityAggregate.getAllTodos(cleaningObject)
	val firstTodo: Todo = activityAggregate.getFirstTodo(todoList)
	val todoText: MutableState<String> = remember { mutableStateOf("$firstTodo") }
	val todoService = TodoService(cleaningObject.address)
	todoOverviewRow(
		"${activityAggregate.activityType}",
		text2 = todoText.value,
		onClick1 = {
			firstTodo.changeStatus()
			todoService.saveTodoList(todoList)
			todoText.value = "$firstTodo"
		},
		"Todo Overview",
		onClick2 = {
			navController.activityIndex = index
			navController.navigate(Screen.TodoOverviewScreen.name)
		},
	)
}
