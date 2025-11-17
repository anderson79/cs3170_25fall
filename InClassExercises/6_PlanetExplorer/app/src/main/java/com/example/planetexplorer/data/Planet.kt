package com.example.planetexplorer.data

import androidx.annotation.DrawableRes
import com.example.planetexplorer.R

data class Planet(
    val id: Int,
    val name: String,
    val imageRes: Int,
    val description: String,
    val diameterKm: Int,         // mean diameter
    val composition: String,     // rocky, gas giant, ice giant, etc.
    val dayLengthHours: Double,
    val yearLengthDays: Double,
    val moons: List<String>
)


val planets = listOf(
    Planet(
        id = 0,
        name = "Mercury",
        imageRes = R.drawable.mercury,
        description = "Mercury is the smallest planet in our solar system.",
        diameterKm = 4879,
        composition = "Rocky planet",
        dayLengthHours = 1407.6,      // ~58.6 Earth days
        yearLengthDays = 88.0,
        moons = emptyList()
    ),
    Planet(
        id = 1,
        name = "Venus",
        imageRes = R.drawable.venus,
        description = "Venus has a thick, toxic atmosphere that traps heat.",
        diameterKm = 12104,
        composition = "Rocky planet",
        dayLengthHours = 5832.5,      // ~243 Earth days, retrograde
        yearLengthDays = 224.7,
        moons = emptyList()
    ),
    Planet(
        id = 2,
        name = "Earth",
        imageRes = R.drawable.earth,
        description = "Earth is our home world, filled with life.",
        diameterKm = 12742,
        composition = "Rocky planet",
        dayLengthHours = 24.0,
        yearLengthDays = 365.25,
        moons = listOf("Moon")
    ),
    Planet(
        id = 3,
        name = "Mars",
        imageRes = R.drawable.mars,
        description = "Mars is a cold desert world often called the Red Planet.",
        diameterKm = 6779,
        composition = "Rocky planet",
        dayLengthHours = 24.6,
        yearLengthDays = 687.0,
        moons = listOf("Phobos", "Deimos")
    ),
    Planet(
        id = 4,
        name = "Jupiter",
        imageRes = R.drawable.jupiter,
        description = "Jupiter is the largest planet and has a Great Red Spot storm.",
        diameterKm = 139820,
        composition = "Gas giant",
        dayLengthHours = 9.9,
        yearLengthDays = 4333.0,      // ~11.9 Earth years
        moons = listOf("Io", "Europa", "Ganymede", "Callisto")
    ),
    Planet(
        id = 5,
        name = "Saturn",
        imageRes = R.drawable.saturn,
        description = "Saturn is famous for its spectacular ring system.",
        diameterKm = 116460,
        composition = "Gas giant",
        dayLengthHours = 10.7,
        yearLengthDays = 10759.0,     // ~29.5 Earth years
        moons = listOf("Titan", "Enceladus", "Rhea", "Iapetus")
    ),
    Planet(
        id = 6,
        name = "Uranus",
        imageRes = R.drawable.uranus,
        description = "Uranus rotates on its side and appears blue-green.",
        diameterKm = 50724,
        composition = "Ice giant",
        dayLengthHours = 17.2,
        yearLengthDays = 30687.0,     // ~84 Earth years
        moons = listOf("Titania", "Oberon", "Umbriel", "Ariel", "Miranda")
    ),
    Planet(
        id = 7,
        name = "Neptune",
        imageRes = R.drawable.neptune,
        description = "Neptune is a windy, icy giant farthest from the Sun.",
        diameterKm = 49244,
        composition = "Ice giant",
        dayLengthHours = 16.1,
        yearLengthDays = 60190.0,     // ~165 Earth years
        moons = listOf("Triton", "Proteus", "Nereid")
    ),
    Planet(
        id = 8,
        name = "Pluto",
        imageRes = R.drawable.pluto,
        description = "Pluto is a small icy world beyond Neptune with a thin atmosphere and a heart-shaped glacier.",
        diameterKm = 2377,
        composition = "Icy dwarf planet (but always a planet at heart!)",
        dayLengthHours = 153.3,       // ~6.4 Earth days
        yearLengthDays = 90560.0,     // ~248 Earth years
        moons = listOf("Charon", "Styx", "Nix", "Kerberos", "Hydra")
    )
)
