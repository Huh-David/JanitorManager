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
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import de.davidhuh.janitormanager.domain.*
import de.davidhuh.janitormanager.service.generateMockData
import de.davidhuh.janitormanager.ui.navcontroller.*
import de.davidhuh.janitormanager.ui.screens.*


@Composable
@Preview
fun App() {
	val cleaningObjectList = generateMockData().sortedBy { it.toString() } as MutableList<CleaningObject>
	val screens = Screen.values().toList()
	val navController by rememberNavController(Screen.HomeScreen.name, mutableSetOf(), cleaningObjectList)
	val currentScreen by remember {
		navController.currentScreen
	}

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
					customNavigationHost(navController = navController)
				}
			}
		}
	}
}

@Composable
fun customNavigationHost(
	navController: NavController,
) {
	NavigationHost(navController) {
		composable(Screen.HomeScreen.name) {
			homeScreen(navController)
		}
		composable(Screen.CleaningObjectScreen.name) {
			cleaningObjectScreen(navController)
		}
		composable(Screen.TodoOverviewScreen.name) {
			todoOverviewScreen(navController)
		}
		composable(Screen.AllTodosScreen.name) {
			allTodosScreen(navController)
		}
	}.build()
}


fun main() = application {
	Window(onCloseRequest = ::exitApplication) {
		App()
	}
}
