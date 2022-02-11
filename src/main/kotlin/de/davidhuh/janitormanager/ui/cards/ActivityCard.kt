package de.davidhuh.janitormanager.ui.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.davidhuh.janitormanager.domain.Activity

@Composable
fun activityCard(
	activity: Activity,
) {
	OutlinedButton(
		modifier = Modifier
			.fillMaxWidth()
			.padding(start = 80.dp),
		onClick = { /* Do something! */ }
	) {
		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			Text("${activity.activityType}")
			Text("${activity.startDate}")
		}
	}
}
