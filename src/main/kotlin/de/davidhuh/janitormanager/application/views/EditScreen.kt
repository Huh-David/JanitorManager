package de.davidhuh.janitormanager.application.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.davidhuh.janitormanager.adapter.service.CleaningObjectService
import de.davidhuh.janitormanager.application.navcontroller.NavController
import de.davidhuh.janitormanager.domain.entity.CleaningObjectManagement
import de.davidhuh.janitormanager.domain.valueobject.Address

@Composable
fun generateAddressChangeFields(
	navController: NavController,
	modifier: Modifier = Modifier,
) {
	val cleaningObject = navController.cleaningObjectList[navController.cleaningObjectIndex]

	val valuesToChange = mapOf<String, MutableState<String>>(
		Pair("City", remember { mutableStateOf(cleaningObject.address.city) }),
		Pair("Zipcode", remember { mutableStateOf(cleaningObject.address.zipcode) }),
		Pair("Street", remember { mutableStateOf(cleaningObject.address.street) }),
		Pair("House Number", remember { mutableStateOf(cleaningObject.address.houseNumber) })
	)

	Column(
		modifier = modifier
			.padding(bottom = 4.dp, end = 4.dp)
			.padding(start = 10.dp, end = 10.dp)
			.wrapContentWidth(Alignment.Start)
			.border(BorderStroke(2.dp, Color.Gray))
			.padding(10.dp)
	) {
		Text("Change Address", fontWeight = FontWeight.Bold)

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
}

@Composable
fun generateCleaningObjectManagementChangeFields(
	navController: NavController,
	modifier: Modifier = Modifier,
) {
	val cleaningObject = navController.cleaningObjectList[navController.cleaningObjectIndex]

	val valuesToChange = mapOf<String, MutableState<String>>(
		Pair("First Name", remember { mutableStateOf(cleaningObject.cleaningObjectManagement.firstName) }),
		Pair("Last Name", remember { mutableStateOf(cleaningObject.cleaningObjectManagement.lastName) }),
		Pair("Phone", remember { mutableStateOf(cleaningObject.cleaningObjectManagement.phone) }),
		Pair("Email", remember { mutableStateOf(cleaningObject.cleaningObjectManagement.email) }),
		Pair("City", remember { mutableStateOf(cleaningObject.cleaningObjectManagement.address.city) }),
		Pair("Zipcode", remember { mutableStateOf(cleaningObject.cleaningObjectManagement.address.zipcode) }),
		Pair("Street", remember { mutableStateOf(cleaningObject.cleaningObjectManagement.address.street) }),
		Pair("House Number", remember { mutableStateOf(cleaningObject.cleaningObjectManagement.address.houseNumber) })
	)

	Column(
		modifier = modifier
			.padding(bottom = 4.dp, end = 4.dp)
			.padding(start = 10.dp, end = 10.dp)
			.wrapContentWidth(Alignment.Start)
			.border(BorderStroke(2.dp, Color.Gray))
			.padding(10.dp)
	) {
		Text("Change Management", fontWeight = FontWeight.Bold)

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
				val newCleaningObjectManagement = CleaningObjectManagement(
					valuesToChange["First Name"]!!.value,
					valuesToChange["Last Name"]!!.value,
					valuesToChange["Phone"]!!.value,
					valuesToChange["Email"]!!.value,
					newAddress
				)
				val cleaningObjectService = CleaningObjectService()

				cleaningObjectService.changeCleaningObjectManagementOfCleaningObject(
					navController.cleaningObjectList,
					cleaningObject,
					newCleaningObjectManagement
				)
			}
		) {
			Text("Save new Management")
		}
	}
}

@Composable
fun generateCleaningObjectEditFields(navController: NavController) {
	Row {
		generateAddressChangeFields(
			navController,
			modifier = Modifier
				.weight(1f)
				.wrapContentWidth(Alignment.Start)
		)
		generateCleaningObjectManagementChangeFields(
			navController,
			modifier = Modifier
				.weight(1f)
				.wrapContentWidth(Alignment.End)
		)
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
