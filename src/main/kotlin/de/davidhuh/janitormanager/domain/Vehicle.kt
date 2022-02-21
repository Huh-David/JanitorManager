package de.davidhuh.janitormanager.domain

class Vehicle(
	val vehicleId: String,
	val brand: String,
	val model: String,
	val hasLoadingArea: Boolean = false,
	val lastUsedBy: Employee?,
	var kilometers: Float,
	val fuelType: FuelType,
) {
	fun addKilometers(kilometers: Float) {
		this.kilometers.plus(kilometers)
	}

	fun subtractKilometers(kilometers: Float) {
		this.kilometers.minus(kilometers)
	}

	enum class FuelType() {
		DIESEL,
		PETROL,
		LPG,
		ELECTRIC
	}
}
