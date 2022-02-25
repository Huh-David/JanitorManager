package de.davidhuh.janitormanager.domain.entity

import de.davidhuh.janitormanager.domain.valueobject.Address

class Carpool(
	var address: Address,
	val vehicles: MutableList<Vehicle>,
) {
	fun changeAddress(newAddress: Address) {
		this.address = newAddress
	}

	fun addVehicleToPool(vehicle: Vehicle) {
		vehicles.add(vehicle)
	}

	fun removeVehicleFromPool(vehicle: Vehicle) {
		vehicles.remove(vehicle)
	}
}
