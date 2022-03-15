package de.davidhuh.janitormanager.plugins.ui.navcontroller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import de.davidhuh.janitormanager.domain.entity.cleaningobject.CleaningObject

class NavController(
	private val startDestination: String,
	private var backStackScreens: MutableSet<String> = mutableSetOf(),
	var cleaningObjectIndex: Int,
	var cleaningObjectList: MutableList<CleaningObject>,
	var activityIndex: Int,
) {
	var currentScreen: MutableState<String> = mutableStateOf(startDestination)

	fun navigate(route: String) {
		if (route != currentScreen.value) {
			if (backStackScreens.contains(currentScreen.value) && currentScreen.value != startDestination) {
				backStackScreens.remove(currentScreen.value)
			}

			if (route == startDestination) {
				backStackScreens = mutableSetOf()
			} else {
				backStackScreens.add(currentScreen.value)
			}

			currentScreen.value = route
		}
	}

	fun navigateBack() {
		if (backStackScreens.isNotEmpty()) {
			currentScreen.value = backStackScreens.last()
			backStackScreens.remove(currentScreen.value)
		}
	}
}


@Composable
fun rememberNavController(
	startDestination: String,
	backStackScreens: MutableSet<String> = mutableSetOf(),
	cleaningObjectList: MutableList<CleaningObject>,

	): MutableState<NavController> = rememberSaveable {
	mutableStateOf(NavController(startDestination, backStackScreens, 0, cleaningObjectList, 0))
}
