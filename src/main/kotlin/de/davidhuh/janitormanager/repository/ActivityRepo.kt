package de.davidhuh.janitormanager.repository

import de.davidhuh.janitormanager.domain.Activity
import de.davidhuh.janitormanager.domain.ActivityType
import de.davidhuh.janitormanager.domain.Todo

class ActivityRepo(
	var activityType: ActivityType,
	var activityList: MutableList<Activity>,
) {
	fun checkCompatibility(activity: Activity): Boolean {
		return activity.activityType == activityType
	}

	private fun checkActivityExistence(activity: Activity): Boolean {
		return !activityList.none { it.startDate == activity.startDate && it.intervalInDays == activity.intervalInDays }
	}

	fun addActivity(activity: Activity): Boolean {
		if (checkCompatibility(activity)) {
			if (!checkActivityExistence(activity)) {
				activityList.add(activity)
				return true
			}
		}
		return false
	}

	fun getAllTodos(): MutableList<Todo> {
		val todoList = mutableListOf<Todo>()

		for (activity in activityList) {
			for (todo in activity.todoList) {
				todoList.add(todo)
			}
		}

		todoList.sortBy { it.date }
		return todoList
	}

	fun getFirstTodo(): Todo {
		val todoList = getAllTodos()
		return if (todoList.none() { !it.done }) {
			todoList.last()
		} else {
			todoList.first() { !it.done }
		}
	}
}
