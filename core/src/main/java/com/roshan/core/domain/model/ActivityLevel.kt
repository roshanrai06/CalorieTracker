package com.roshan.core.domain.model

sealed class ActivityLevel(val name: String) {
    object Low : ActivityLevel("low")
    object High : ActivityLevel("high")
    object Medium : ActivityLevel("medium")

    companion object {
        fun fromString(name: String): ActivityLevel {
            return when (name) {
                "low" -> Low
                "high" -> High
                "medium" -> Medium
                else -> Medium
            }
        }
    }
}
