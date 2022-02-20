package de.davidhuh.janitormanager.domain

class Vehicle(
	val vehicleId: String,
	val brand: String,
	val model: String,
	val hasLoadingArea: Boolean = false,
	var kilometers: Float,
	val fuelType: FuelType, // TODO to ENUM
) {

	enum class FuelType() {
		DIESEL,
		PETROL,
		LPG,
		ELECTRIC
	}
}
