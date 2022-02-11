// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import de.davidhuh.janitormanager.domain.*
import de.davidhuh.janitormanager.ui.navcontroller.*
import de.davidhuh.janitormanager.ui.screens.HomeScreen
import de.davidhuh.janitormanager.ui.screens.Screen
import de.davidhuh.janitormanager.ui.screens.TestScreen
import java.time.LocalDate
import kotlin.random.Random



@Composable
fun activityCard(
	activity: Activity,
) {
	OutlinedButton(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 10.dp),
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

@Composable
@Preview
fun App() {

	val screens = Screen.values().toList()
	val navController by rememberNavController(Screen.HomeScreen.name)
	val currentScreen by remember {
		navController.currentScreen
	}

//	val cleaningObjectList = generateMockData()
//
//	MaterialTheme {
//		Column {
//			cleaningObjectList.forEach { cleaningObject ->
//				cleaningObjectCard(cleaningObject)
//			}
//		}
//	}

	MaterialTheme {
		Surface(
			modifier = Modifier.background(color = MaterialTheme.colors.background)
		) {
			Box(
				modifier = Modifier.fillMaxSize()
			) {
				NavigationRail(
					modifier = Modifier.align(Alignment.CenterStart).fillMaxHeight()
				) {
					screens.forEach {
						NavigationRailItem(
							selected = currentScreen == it.name,
							icon = {
								Icon(
									imageVector = it.icon,
									contentDescription = it.label
								)
							},
							label = {
								Text(it.label)
							},
							alwaysShowLabel = false,
							onClick = {
								navController.navigate(it.name)
							}
						)
					}
				}

				Box(
					modifier = Modifier.fillMaxHeight()
				) {
					CustomNavigationHost(navController = navController)
				}
			}
		}
	}
}

@Composable
fun CustomNavigationHost(
	navController: NavController,
) {
	NavigationHost(navController) {
		composable(Screen.HomeScreen.name) {
			HomeScreen(navController)
		}
		composable(Screen.TestScreen.name) {
			TestScreen(navController)
		}
	}.build()
}


fun main() = application {
	Window(onCloseRequest = ::exitApplication) {
		App()
	}
}
