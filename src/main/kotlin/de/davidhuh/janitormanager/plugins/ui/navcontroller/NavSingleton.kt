package de.davidhuh.janitormanager.plugins.ui.navcontroller

import de.davidhuh.janitormanager.domain.entity.cleaningobject.CleaningObject

class NavSingleton(
	var cleaningObjectList: MutableList<CleaningObject>,
) {
	var activityIndex: Int = 0
	var cleaningObjectIndex: Int = 0
}
