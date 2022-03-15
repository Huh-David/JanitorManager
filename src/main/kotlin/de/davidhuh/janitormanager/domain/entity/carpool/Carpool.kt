package de.davidhuh.janitormanager.domain.entity.carpool

import de.davidhuh.janitormanager.domain.entity.vehicle.Vehicle
import de.davidhuh.janitormanager.domain.valueobject.address.Address

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
