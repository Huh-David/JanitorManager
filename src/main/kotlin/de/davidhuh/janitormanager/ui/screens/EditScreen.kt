package de.davidhuh.janitormanager.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import de.davidhuh.janitormanager.ui.navcontroller.NavController

@Composable
fun editScreen(
	navController: NavController,
) {
	templateScreen() {
		val text = remember { mutableStateOf("Hello") }

		OutlinedTextField(
			value = text.value,
			onValueChange = { text.value = it },
			label = { Text("Label") }
		)
	}
}
