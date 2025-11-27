package com.example.charactercreator.data

/**
 * DataSourcce.kt
 *
 * This file holds the information for the default characters
 *
 * dummyChar - a Character you can use in the app preview so we have data to use
 *
 * dummyUiState - a state with the dummyChar, as well as the isCustom field set to true and the
 *                  field arePointsAllSpent correctly initialized
 */

import androidx.annotation.DrawableRes
import com.example.charactercreator.R
import com.example.charactercreator.model.CharacterCreatorUiState
import com.example.charactercreator.model.Character

object DataSource {

    // a dummyCharacter we can use in our previews
    val dummyChar = Character.makeCharacter(
        name = "Android",
        charClass = "Mobile Phone",
        description = "Android is a Mobile Phone who, among other things, can tell you what the weather is currently in Antarcica",
        power = 1,
        endurance = 2,
        speed = 3,
        focus = 4,
        characterColor = R.color.teal_700,
        characterImage = R.drawable.ic_android_black_24dp,
        classIcon = R.drawable.baseline_catching_pokemon_24,
        characterIcon = R.drawable.baseline_smartphone_24,
    )

    // a dummyUiState we can use in our previews
    val dummyUiState = CharacterCreatorUiState(
        currentCharacter = dummyChar,
        isCustom = true,
        areAllPointsSpent = dummyChar.arePointsAllSpent()
    )


    // Create a list of four default characters
    // It's our responsibility to make sure the points don't go past the max point total
    // OR - we can use these points to figure out what the point total should be for any character
    val defaultCharacters = listOf(
        Character.makeCharacter(
            name = "Thorin",
            charClass = "Warrior",
            description = "A battle-hardened fighter who relies on brute strength and endurance.",
            power = 4,
            endurance = 3,
            speed = 2,
            focus = 1,
            characterColor = R.color.warrior_color,
            characterImage = R.drawable.warrior_crest,
            classIcon = R.drawable.swords_24px,
            characterIcon = R.drawable.baseline_heart_broken_24,
        ),
        Character.makeCharacter(
            name = "Lyra",
            charClass = "Rogue",
            description = "A quick and cunning thief who strikes from the shadows.",
            power = 2,
            endurance = 2,
            speed = 4,
            focus = 2,
            characterColor = R.color.rogue_color,
            characterImage = R.drawable.rogue_crest,
            classIcon = R.drawable.dark_mode_24px,
            characterIcon = R.drawable.baseline_electric_bolt_24,
        ),
        Character.makeCharacter(
            name = "Seraphine",
            charClass = "Paladin",
            description = "A holy knight devoted to protecting allies and vanquishing evil.",
            power = 3,
            endurance = 3,
            speed = 2,
            focus = 2,
            characterColor = R.color.paladin_color,
            characterImage = R.drawable.paladin_crest,
            classIcon = R.drawable.flare_24px,
            characterIcon = R.drawable.baseline_security_24,
        ),
        Character.makeCharacter(
            name = "Eldrin",
            charClass = "Mage",
            description = "A master of arcane arts who channels focus into devastating spells.",
            power = 1,
            endurance = 1,
            speed = 1,
            focus = 7,
            characterColor = R.color.mage_color,
            characterImage = R.drawable.mage_crest,
            classIcon = R.drawable.wand_stars_24px,
            characterIcon = R.drawable.baseline_self_improvement_24,
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

    /**
     * Helper function to convert an array of stats/attributes into a map of
     * statName: statValue
     *
     * @param statArray the stat values in parallel with the stats in statInfoList
     * @param statInfoList list of StatInfo, uses the names as the keys
     * @return map of statName from statInfoList to statValue from statArray
     */
    fun mapFromArray(
        statArray: Array<Int>,
        statInfoList: List<StatInfo>
    ): Map<String, Int> {
        return statInfoList
            .map {statInfo -> statInfo.name}
            .zip(statArray)
            .toMap()
    }
}