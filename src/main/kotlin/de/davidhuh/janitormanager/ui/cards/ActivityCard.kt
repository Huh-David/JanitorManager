package de.davidhuh.janitormanager.ui.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.davidhuh.janitormanager.domain.Activity
import de.davidhuh.janitormanager.domain.Todo

@Composable
fun activityCard(
	activity: Activity,
) {
	var firstTodo: Todo
	if (activity.todoList.filter { !it.done }.isEmpty()) {
		firstTodo = remember { activity.todoList.last() }
	} else {
		firstTodo = remember { activity.todoList.first { !it.done } }
	}

	val text: MutableState<String> = remember { mutableStateOf("$firstTodo") }

	OutlinedButton(
		modifier = Modifier
			.fillMaxWidth()
			.padding(start = 80.dp),
		onClick = {
			firstTodo.done = !firstTodo.done
			firstTodo.changeStatus()
			text.value = "$firstTodo"
			print(text)
		}
	) {
		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			Text("${activity.activityType}")
			Text(text = text.value)
		}
	}
}
