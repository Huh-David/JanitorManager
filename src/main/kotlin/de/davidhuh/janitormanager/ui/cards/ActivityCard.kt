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
import de.davidhuh.janitormanager.repository.ActivityRepo
import de.davidhuh.janitormanager.ui.navcontroller.NavController
import de.davidhuh.janitormanager.ui.screens.Screen

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
	activityRepo: ActivityRepo,
	index: Int,
	navController: NavController,
) {
	val firstTodo: Todo = remember { activityRepo.getFirstTodo() }
	val todoText: MutableState<String> = remember { mutableStateOf("$firstTodo") }

	todoOverviewRow(
		"${activityRepo.activityType}",
		text2 = todoText.value,
		onClick1 = {
			firstTodo.changeStatus()
			todoText.value = "$firstTodo"
		},
		"Todo Overview",
		onClick2 = {
			navController.activityIndex = index
			navController.navigate(Screen.TodoOverviewScreen.name)
		},
	)
}
