package de.davidhuh.janitormanager.domain

class Vehicle(
	val vehicleId: String,
	val brand: String,
	val model: String,
	val hasLoadingArea: Boolean = false,
	var lastUsedBy: Employee?,
	var currentlyUsed: Boolean = false,
	var kilometers: Float,
	var fuelType: FuelType,
) {
	fun addKilometers(kilometers: Float) {
		this.kilometers = this.kilometers.plus(kilometers)
	}

	fun subtractKilometers(kilometers: Float) {
		this.kilometers = this.kilometers.minus(kilometers)
	}

	fun changeUser(employee: Employee) {
		this.lastUsedBy = employee
		this.currentlyUsed = true
	}

	fun changeFuelType(fuelType: FuelType) {
		this.fuelType = fuelType
	}

	enum class FuelType() {
		DIESEL,
		PETROL,
		LPG,
		ELECTRIC
	}
}
