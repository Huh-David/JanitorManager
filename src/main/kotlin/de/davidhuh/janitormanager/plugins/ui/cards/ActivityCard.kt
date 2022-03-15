package de.davidhuh.janitormanager.plugins.ui.cards

import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import de.davidhuh.janitormanager.application.activity.ActivityAggregateService
import de.davidhuh.janitormanager.plugins.persistence.todo.TodoPersistenceRepo
import de.davidhuh.janitormanager.plugins.ui.navcontroller.NavController
import de.davidhuh.janitormanager.plugins.ui.views.Screen
import de.davidhuh.janitormanager.domain.entity.todo.Todo
import de.davidhuh.janitormanager.domain.entity.activity.ActivityAggregate

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
	val activityAggregateService = ActivityAggregateService(activityAggregate, cleaningObject)
	val todoPersistenceRepo = TodoPersistenceRepo(cleaningObject.address)
	val todoList = activityAggregateService.getAllTodos(todoPersistenceRepo)
	val firstTodo: Todo = activityAggregateService.getFirstTodo(todoList)
	val todoText: MutableState<String> = remember { mutableStateOf("$firstTodo") }
	todoOverviewRow(
		"${activityAggregate.activityType}",
		text2 = todoText.value,
		onClick1 = {
			firstTodo.changeStatus()
			todoPersistenceRepo.saveTodoList(todoList)
			todoText.value = "$firstTodo"
		},
		"Todo Overview",
		onClick2 = {
			navController.activityIndex = index
			navController.navigate(Screen.TodoOverviewScreen.name)
		},
	)
}
