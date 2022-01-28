package com.roshan.core.domain.model

sealed class GoalType(val name: String) {
    object LoseWeight : GoalType("lose_weight")
    object KeepWeight : GoalType("keep_weight")
    object GainWeight : GoalType("gain_weight")

    companion object {
        fun fromString(name: String): GoalType {
            return when (name) {
                "lose_weight" -> LoseWeight
                "gain_weight" -> GainWeight
                "keep_weight" -> KeepWeight
                else -> KeepWeight
            }
        }
    }

}
