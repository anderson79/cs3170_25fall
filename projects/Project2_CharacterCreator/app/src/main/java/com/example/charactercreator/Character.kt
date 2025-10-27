/**
 * Character.kt
 *
 * This file contains the Character class definition, as well as other data such as
 * what Icon is associated with each stat or attribute.
 */
package com.example.charactercreator.com.example.charactercreator

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.charactercreator.R

data class StatInfo(
    val name: String,
    @DrawableRes val icon: Int,
    val description: String
)

val statList = listOf(
    StatInfo("Power", R.drawable.construction_24px, "Increases attack damage"),
    StatInfo("Endurance", R.drawable.baseline_fitness_center_24, "Improves defense"),
    StatInfo("Speed", R.drawable.baseline_bolt_24, "Boosts agility"),
    StatInfo("Focus", R.drawable.baseline_visibility_24, "Reduces cost")
)

val attributesList = listOf(
    StatInfo("Attack", R.drawable.swords_24px, "Amount of damage done"),
    StatInfo("Defense", R.drawable.baseline_security_24, "Amount of damage blocked"),
    StatInfo("Cost", R.drawable.baseline_price_change_24, "Cost for casting this character"),
    )

data class Character(
    val name: String = "",
    val charClass: String= "",
    val description: String = "",

    val statMap: Map<String, Int> = mapOf(
        "Power" to 4,
        "Endurance" to 1,
        "Speed" to 0,
        "Focus" to 5
    ),

    // can use parallel arrays
    val statArray: Array<Int> = arrayOf(0,0,0,0),

    // can use individual variables
    val power :Int = 0,
    val endurance: Int= 0,

    val attributes: Map<String, Int> = mapOf(
        "Attack" to 0,
        "Defense" to 0,
        "Cost" to 0
    ),
    val maxPoints: Int = 10

//    val total = power + endurance + speed + focus
//    val remaining = maxPoints - total
//
//    val attack = (2 * power + 0.5 * speed).toInt()
//    val defense = (2 * endurance + 0.5 * speed).toInt()
//    val cost = ((attack + defense) / 4.0 + 0.5 * focus).toInt()
) {
    val totalPoints: Int
        get() = statMap.values.sum()

    /** Returns a copy with updated stats and recalculated attributes. */
    fun withUpdatedStat(statName: String, delta: Int, maxPoints: Int): Character {
        val newStats = statMap.toMutableMap()
        val currentValue = newStats[statName] ?: 0
        val newValue = (currentValue + delta).coerceAtLeast(0)

        // Prevent exceeding total pool
        if (delta > 0 && totalPoints >= maxPoints) return this
        if (newValue == currentValue) return this

        newStats[statName] = newValue
        return copy(
            statMap = newStats,
            attributes = computeAttributes(newStats)
        )
    }

    /** Returns a copy with updated stats and recalculated attributes. */
    fun withUpdatedStat(statIndex: Int, delta: Int, maxPoints: Int): Character {
        val newStats = statArray.clone()
        val currentValue = newStats[statIndex]
        val newValue = (currentValue + delta).coerceAtLeast(0)

        // Prevent exceeding total pool
        if (delta > 0 && totalPoints >= maxPoints) return this
        if (newValue == currentValue) return this

        newStats[statIndex] = newValue
        return copy(
            statArray = newStats,
            //attributes = computeAttributes(newStats)
        )
    }

    companion object {
        /** Computes Attack, Defense, and Cost from the current stats. */
        fun computeAttributes(stats: Map<String, Int>): Map<String, Int> {
            val power = stats["Power"] ?: 0
            val endurance = stats["Endurance"] ?: 0
            val speed = stats["Speed"] ?: 0
            val focus = stats["Focus"] ?: 0

            val attack = (2 * stats.getOrDefault("Power", 0)) +
                    (stats.getOrDefault("Speed", 0) / 2)
            val defense = (2 * stats.getOrDefault("Endurance", 0)) +
                    (stats.getOrDefault("Speed", 0) / 2)
            val cost = ((attack + defense) / 4) + (stats.getOrDefault("Focus", 0) / 2)

            return mapOf(
                "Attack" to attack,
                "Defense" to defense,
                "Cost" to cost
            )
        }
    }

    init {
        println("Character recomposed: name=$name")
    }
}


/** Compose helper: creates a mutable Character that triggers recomposition when changed. */
@Composable
fun rememberCharacterState(): MutableState<Character> {
    return remember { mutableStateOf(Character()) }
}

/** Utility for updating a stat while preserving immutability. */
fun updateStat(
    characterState: MutableState<Character>,
    statName: String,
    delta: Int,
    maxPoints: Int
) {
    val current = characterState.value
    val updated = current.withUpdatedStat(statName, delta, maxPoints)
    if (updated != current) {
        characterState.value = updated
    }
}

fun updateStat(    characterState: MutableState<Character>,
                   statIndex: Int,
                   delta: Int,
                   maxPoints: Int) {
    val current = characterState.value
    val updated = current.withUpdatedStat(statIndex, delta, maxPoints)
    if (updated != current) {
        characterState.value = updated
    }
}
