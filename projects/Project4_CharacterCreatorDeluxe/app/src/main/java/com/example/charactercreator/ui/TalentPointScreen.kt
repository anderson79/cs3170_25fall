package com.example.charactercreator.ui
/**
 * ----------------------------------------------------------------------------
 * Project 4 Starter Code – Talent Point Screen (Screen 2)
 * ----------------------------------------------------------------------------
 *
 * This screen allows the user to spend talent points on their character.
 * You built a version of this in Project 3 — now you will integrate that
 * logic into this multi-screen app.
 *
 * WHAT THIS SCREEN SHOULD DO:
 *   • Display each stat (Power, Endurance, Speed, Focus)
 *   • For each stat, show + and – buttons (StatButtons composable)
 *   • Update stat values when buttons are pressed
 *   • Prevent spending more than maxPoints
 *   • Show how many points are remaining
 *   • Add navigation buttons (Back, Next, and optionally Reset)
 *
 * WHAT THIS FILE ALREADY PROVIDES:
 *   ✓ A layout with a LazyVerticalGrid
 *   ✓ Access to the current character through uiState
 *   ✓ Callback parameters: updateStat(), onBackClick(), onNextClick(), etc.
 *   ✓ A Preview to help you design your UI
 *
 * WHAT YOU MUST IMPLEMENT:
 *   • Fill in StatButtons:
 *        statName = stat.name
 *        value = character.statMap[stat.name].toString()
 *        onPlusClick = { updateStat(...) }
 *        onMinusClick = { updateStat(...) }
 *
 *   • Display remaining points
 *   • (Optional) Show the computed attributes (Attack, Defense, Cost)
 *   • Add and connect Back, Next, and Reset buttons
 *
 * TIP:
 *   - The ViewModel handles stat validation using withUpdatedStat().
 *   - updateStat(statName, delta, maxPoints) will call that logic.
 * ----------------------------------------------------------------------------
 */

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.charactercreator.data.DataSource
import com.example.charactercreator.data.DataSource.statList
import com.example.charactercreator.model.CharacterCreatorUiState
import com.example.charactercreator.ui.theme.CharacterCreatorTheme
import com.example.charactercreator.ui.util.AttributeList
import com.example.charactercreator.ui.util.StatButtons

@Composable
fun TalentPointScreen(
    uiState: CharacterCreatorUiState,
    updateStat: (String, Int, Int) -> Unit, // need to pass in the stat name/index, +1 or -1, and character.maxPoints
    onBackClick: () -> Unit,
    onResetClick: () -> Unit,
    onNextClick: () -> Unit,
    enableNextButton: Boolean,
    modifier: Modifier = Modifier
) {
    /**
     * uiState.currentCharacter holds all current stat values.
     *
     * updateStat() is the ONLY function you should call to modify stats.
     *      updateStat(statName, +1, character.maxPoints)
     *      updateStat(statName, -1, character.maxPoints)
     *
     * The ViewModel will automatically compute attributes and enforce rules
     * such as:
     *      - stats cannot go below 0
     *      - total points cannot exceed maxPoints
     */

    val character = uiState.currentCharacter
    val remainingPoints = character.maxPoints - character.totalPoints

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp)
        ) {
            // --------------------------------------------------------------------
            // STAT BUTTON GRID
            // --------------------------------------------------------------------
            // statList comes from DataSource and contains:
            //    • a stat name (e.g., "Power")
            //    • an icon for that stat
            //
            // For each stat:
            //   - Display StatButtons()
            //   - Show its current value from character.statMap
            //   - Call updateStat() when + or – is pressed
            //
            // EXAMPLE you will implement:
            //      statName = stat.name
            //      value = (character.statMap[stat.name] ?: 0).toString()
            //      onPlusClick = { updateStat(stat.name, +1, character.maxPoints) }
            //      onMinusClick = { updateStat(stat.name, -1, character.maxPoints) }
            //
            // --------------------------------------------------------------------
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(0.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // this loops through the statList and creates a set of buttons for each stat
                items(statList) { stat ->
                    StatButtons(
                        statName = "", // TODO
                        value = "", // TDOO
                        iconId = stat.icon,
                        onPlusClick = { /* TODO */ },
                        onMinusClick = { /* TODO */ },
                    )
                }
            }
        }

        // --------------------------------------------------------------------
        // REMAINING POINTS
        // --------------------------------------------------------------------
        // Show the number of points left to spend.
        // The value is already computed for you:
        //
        //      val remainingPoints = character.maxPoints - character.totalPoints
        //
        // TODO: Display it using a Text composable.
        // --------------------------------------------------------------------


        // --------------------------------------------------------------------
        // OPTIONAL: ATTRIBUTE PREVIEW (Attack, Defense, Cost)
        // --------------------------------------------------------------------
        // AttributeList() shows the derived stats based on your
        // talent point allocation.
        //
        // This is a helpful preview, but not required.
        //
        // Uncomment something like:
        //      AttributeList(stats = character.statMap)
        //
        // --------------------------------------------------------------------


        // --------------------------------------------------------------------
        // NAVIGATION BUTTONS
        // --------------------------------------------------------------------
        // You must add buttons that perform the following:
        //
        //   Back:
        //       Calls onBackClick()
        //
        //   Reset:
        //       Calls onResetClick()
        //       Should reset stats AND stay on this screen
        //       (or navigate back to ClassSelection if you prefer)
        //
        //   Next:
        //       Calls onNextClick()
        //       Should only be enabled when your conditions are met
        //
        // TODO: Add Buttons and wire them to the provided callbacks.
        // --------------------------------------------------------------------
    }
}

@Preview
@Composable
fun TalentPointScreenPreview() {
    CharacterCreatorTheme {
        TalentPointScreen(
            uiState = DataSource.dummyUiState,
            updateStat = { name: String, delta: Int, maxPts: Int -> },
            onBackClick = {}, onResetClick = {}, onNextClick = {},
            enableNextButton = false
        )
    }
}