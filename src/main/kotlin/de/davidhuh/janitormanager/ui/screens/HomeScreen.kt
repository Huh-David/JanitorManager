package de.davidhuh.janitormanager.ui.screens

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.davidhuh.janitormanager.ui.cards.cleaningObjectCard
import de.davidhuh.janitormanager.ui.navcontroller.NavController

@Composable
fun homeScreen(
	navController: NavController,
) {

	val stateVertical = rememberScrollState(0)

	VerticalScrollbar(
		modifier = Modifier
			.fillMaxHeight(),
		adapter = rememberScrollbarAdapter(stateVertical)
	)

	Column(
		modifier = Modifier
			.padding(start = 80.dp)
			.verticalScroll(stateVertical)
	) {
		navController.cleaningObjectList.forEachIndexed { index, _ ->
			cleaningObjectCard(
				index,
				navController
			)
		}
	}
}
