package de.davidhuh.janitormanager.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen(
	val label: String,
	val icon: ImageVector,
) {
	HomeScreen(
		label = "Home",
		icon = Icons.Filled.Home
	),
	TestScreen(
		label = "Test",
		icon = Icons.Filled.AccountBox
	)
}
