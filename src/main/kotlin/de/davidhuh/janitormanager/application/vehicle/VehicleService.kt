package de.davidhuh.janitormanager.application.vehicle

import de.davidhuh.janitormanager.domain.entity.carpool.Carpool
import de.davidhuh.janitormanager.domain.entity.employee.Employee
import de.davidhuh.janitormanager.domain.entity.vehicle.Vehicle

class VehicleService(private val carpool: Carpool) {
	fun getAllVehiclesWithLoadingArea(negate: Boolean = false): List<Vehicle> {
		return if (negate) {
			this.carpool.vehicles.filter { !it.hasLoadingArea }
		} else {
			this.carpool.vehicles.filter { it.hasLoadingArea }
		}
	}

	fun getAllVehiclesWithMoreThan(kilometers: Float): List<Vehicle> {
		return this.carpool.vehicles.filter { it.kilometers > kilometers }
	}

	fun getAllVehiclesLastUsedBy(employee: Employee?): List<Vehicle> {
		return this.carpool.vehicles.filter { it.lastUsedBy == employee }
	}

	fun getAllVehiclesCurrentlyUsed(negate: Boolean = false): List<Vehicle> {
		return if (negate) {
			this.carpool.vehicles.filter { !it.currentlyUsed }
		} else {
			this.carpool.vehicles.filter { it.currentlyUsed }
		}
	}
}
