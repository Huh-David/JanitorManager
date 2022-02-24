package de.davidhuh.janitormanager.repository

import de.davidhuh.janitormanager.domain.entity.Carpool
import de.davidhuh.janitormanager.domain.entity.Employee
import de.davidhuh.janitormanager.domain.entity.Vehicle

class VehicleRepo(private val carpool: Carpool) {
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
