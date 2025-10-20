package com.example.charactercreator

/**
 * Project 2 – Character Creator
 *
 * Complete this file by implementing the TODO sections.
 * The Character data class and stat logic are provided in Character.kt.
 *
 * Focus on:
 * - Composable layout and organization
 * - State management and callbacks
 * - Displaying dynamic, reactive UI
 */

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.charactercreator.com.example.charactercreator.rememberCharacterState
import com.example.charactercreator.com.example.charactercreator.statList
import com.example.charactercreator.ui.theme.CharacterCreatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CharacterCreatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CharacterCreatorApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CharacterCreatorApp(
    modifier: Modifier = Modifier,
) {
    // You will use characterState to update the values in the character
    val characterState = rememberCharacterState()
    // You can use just character to access the values, but changing them here will not update the state!
    val character = characterState.value
    // You can thank me later, but here is the difficult task of calculating how many points are left
    val remainingPoints = character.maxPoints - character.totalPoints


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Text(
            text = "Character Creator", fontSize = 36.sp,
        )

        // example of looping through list to get all stat strings
        statList.forEach { statInfo ->
            val statVal = character.statMap.getOrElse(statInfo.name, { 0 })
            Text("${statInfo.name} $statVal")
        }
        val statVal = character.statMap.getOrElse("Invalid", { 0 })
        Text("Invalid: $statVal")

        // can loop through an array to get stats
        for (i in 0..statList.size - 1) {
            Text("${statList[i].name}: ${character.statArray[i]}")
        }

        // this "works" but it wont actually do what we need it to do
        character.statArray[2] = 3
        
        // Optional: display character name and class at the top under "Character Creator"

        // TODO Use composable to input name, class, and description
        // Example: onNameChange = { newName -> characterState.value = character.copy(name = newName) }
        // Optional: can use a DropdownMenu and use a list of classes to choose from
        // Optional: can have a Button or placeholder for selecting or showing character image

        // TODO Add 4 StatButtons to inc/dec each stat. These can be placed, two rows of two sets of buttons, or you can think of a way to use a LazyVerticalGrid to loop through statList and create 4 sets of buttons that way
        // Each StatButton should call updateStat() with +/- 1

        // TODO: show remaining points (simple Text here is good)

        // TODO: show summary of Attack, Defense, and Cost
    }
}

// TODO: TextEntry composable
// Needs: Character info (name, class, description), lambdas for when name, class, and description changes
// Optional: Modifier

// TODO: EnterTextField composable
// Needs: value to display, lambda to handle when the value changes (when user types)
// Optional: String (or String resource id) for label, Icon (or Icon resource ID) to put in the TextField, Modifier

// TODO: StatButtons composable
// Needs: stat name, stat value, lambdas for increment and decrementing
// Optional: Icon (or Icon resource ID) to show with each stat, Modifier

// TODO: AttributeList Composable
// Needs: map of stats: values
// Optional: Modifier

@Preview(showBackground = true)
@Composable
fun CharacterCreatorPreview() {
    CharacterCreatorTheme {
        CharacterCreatorApp(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    }
}