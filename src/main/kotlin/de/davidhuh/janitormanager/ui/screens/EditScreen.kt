package de.davidhuh.janitormanager.ui.screens

import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import de.davidhuh.janitormanager.domain.Address
import de.davidhuh.janitormanager.domain.CleaningObject
import de.davidhuh.janitormanager.service.CleaningObjectService
import de.davidhuh.janitormanager.ui.navcontroller.NavController

@Composable
fun generateCleaningObjectEditFields(navController: NavController) {
	val cleaningObject = navController.cleaningObjectList[navController.cleaningObjectIndex]
	val oldAddress = cleaningObject.address

	val valuesToChange = mapOf<String, MutableState<String>>(
		Pair("City", remember { mutableStateOf(oldAddress.city) }),
		Pair("Zipcode", remember { mutableStateOf(oldAddress.zipcode) }),
		Pair("Street", remember { mutableStateOf(oldAddress.street) }),
		Pair("House Number", remember { mutableStateOf(oldAddress.houseNumber) })
	)

	for (valueToChange in valuesToChange) {
		OutlinedTextField(
			value = valueToChange.value.value,
			onValueChange = {
				valueToChange.value.value = it
			},
			label = { Text(valueToChange.key) }
		)
	}

	OutlinedButton(
		onClick = {
			val newAddress = Address(
				valuesToChange["Street"]!!.value,
				valuesToChange["House Number"]!!.value,
				valuesToChange["Zipcode"]!!.value,
				valuesToChange["City"]!!.value,
			)
			val cleaningObjectService = CleaningObjectService()

			cleaningObjectService.changeAddressOfCleaningObject(
				navController.cleaningObjectList,
				cleaningObject,
				newAddress
			)
		}
	) {
		Text("Save new Address")
	}
}

@Composable
fun editScreen(
	navController: NavController,
) {
	templateScreen() {
		generateCleaningObjectEditFields(navController)
	}
}
