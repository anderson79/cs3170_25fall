package com.example.charactercreator.data

import androidx.annotation.DrawableRes
import com.example.charactercreator.R
import com.example.charactercreator.makeCharacter

object DataSource {

    // Create a list of four default characters
    // It's our responsibility to make sure the points don't go past the max point total
    // OR - we can use these points to figure out what the point total should be for any character
    val defaultCharacters = listOf(
        makeCharacter(
            name = "Thorin",
            charClass = "Warrior",
            description = "A battle-hardened fighter who relies on brute strength and endurance.",
            power = 4,
            endurance = 3,
            speed = 2,
            focus = 1
        ),
        makeCharacter(
            name = "Lyra",
            charClass = "Rogue",
            description = "A quick and cunning thief who strikes from the shadows.",
            power = 2,
            endurance = 2,
            speed = 4,
            focus = 2
        ),
        makeCharacter(
            name = "Seraphine",
            charClass = "Paladin",
            description = "A holy knight devoted to protecting allies and vanquishing evil.",
            power = 3,
            endurance = 3,
            speed = 2,
            focus = 2
        ),
        makeCharacter(
            name = "Eldrin",
            charClass = "Mage",
            description = "A master of arcane arts who channels focus into devastating spells.",
            power = 1,
            endurance = 1,
            speed = 1,
            focus = 7
        )
    )

    // this little class is just to group the stat/attribute names with their icon and a short
// description
    data class StatInfo(
        val name: String, @DrawableRes val icon: Int, val description: String
    )

    // statList has a StatInfo for each stat to group the name, icon, and short description of each stst
    val statList = listOf(
        StatInfo("Power", R.drawable.construction_24px, "Increases attack damage"),
        StatInfo("Endurance", R.drawable.baseline_fitness_center_24, "Improves defense"),
        StatInfo("Speed", R.drawable.baseline_bolt_24, "Boosts agility"),
        StatInfo("Focus", R.drawable.baseline_visibility_24, "Reduces cost")
    )

    // similar to statList, attrib list has a StatInfo for each attribute
    val attribList = listOf(
        StatInfo("Attack", R.drawable.swords_24px, "Amount of damage done"),
        StatInfo("Defense", R.drawable.baseline_security_24, "Amount of damage blocked"),
        StatInfo("Cost", R.drawable.baseline_price_change_24, "Cost for casting this character"),
    )
}