package de.davidhuh.janitormanager.plugins.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen(
	val label: String,
	val icon: ImageVector,
) {
	HomeScreen(
		label = "Home",
		icon = Icons.Filled.List
	),
	CleaningObjectScreen(
		label = "Cleaning Object",
		icon = Icons.Filled.Home
	),
	TodoOverviewScreen(
		label = "Todo Overview",
		icon = Icons.Filled.CheckCircle
	),
	AllTodosScreen(
		label = "All Todos",
		icon = Icons.Filled.Check
	),
	EditScreen(
		label = "All Todos",
		icon = Icons.Filled.Add
	)
}
