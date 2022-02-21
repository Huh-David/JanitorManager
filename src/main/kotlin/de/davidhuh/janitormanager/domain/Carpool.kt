package de.davidhuh.janitormanager.domain

class Carpool(
	val address: Address,
	val vehicles: MutableList<Vehicle>,
) {
	fun addVehicleToPool(vehicle: Vehicle) {
		vehicles.add(vehicle)
	}
	fun removeVehicleToPool(vehicle: Vehicle) {
		vehicles.remove(vehicle)
	}
}
