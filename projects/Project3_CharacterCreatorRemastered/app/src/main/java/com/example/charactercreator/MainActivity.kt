package com.example.charactercreator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.charactercreator.data.DataSource.statList
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

    val characterState = rememberCharacterState()
    val character = characterState.value
    val remainingPoints = character.maxPoints - character.totalPoints
    //characterState.value.updateStat()
    //characterViewModel.updateStat()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Text(
            text = "Character Creator", fontSize = 36.sp,
            //modifier = Modifier.padding(bottom = 64.dp)
        )

        // Optional: display character name and class at the top under "Character Creator"

        Text(
            text = character.name, fontSize = 24.sp,
        )
        Text(
            text = character.charClass, fontSize = 24.sp,
        )

        // TODO Add composable to input name, class, and description
        // Example: onNameChange = { newName -> characterState.value = character.copy(name = newName) }
        TextEntry(
            character = character,
            onNameChange = { newName ->
                characterState.value = characterState.value.copy(name = newName)
            },
            { newClass ->
                characterState.value = characterState.value.copy(charClass = newClass)
            },
            { newDesc ->
                characterState.value = characterState.value.copy(description = newDesc)
            },
            modifier = Modifier.padding(24.dp)
        )
        Spacer(modifier = Modifier.padding(16.dp))

        // TODO Add 4 StatButtons to inc/dec each stat. These can be placed, two rows of two sets of buttons, or you can think of a way to use a LazyVerticalGrid to loop through statList and create 4 sets of buttons that way
        // Each StatButton should call updateStat() with +/- 1
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(0.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(statList)
                { stat ->
                    StatButtons(
                        statName = stat.name,
                        value = (character.statMap[stat.name] ?: 0).toString(),
                        iconId = stat.icon,
                        onPlusClick = {
                            updateStat(
                                characterState,
                                stat.name,
                                1,
                                character.maxPoints
                            )
                        },
                        //{
//                            if (character.totalPoints < MAX_STATS) {
//                                setStatMap((statMap + (stat to statMap[stat]?.plus(1))) as HashMap<String, Int>)
//                            }

                        //},
                        onMinusClick = {
                            updateStat(
                                characterState,
                                stat.name,
                                -1,
                                character.maxPoints
                            )
                        },
//                            {
//                            if (statMap.getOrDefault(stat, 0) > 0) {
//                                setStatMap((statMap + (stat to statMap[stat]?.minus(1))) as HashMap<String, Int>)
//                            }
//                        },
                    )
                }

            }
        }

        // TODO: show remaining points (simple Text here is good)
        Spacer(Modifier.padding(vertical = 8.dp))
        Text(text = "Points remaining: ${remainingPoints}/${character.maxPoints}")
        Spacer(Modifier.padding(vertical = 8.dp))

        // TODO: show summary of Attack, Defense, and Cost
        AttributeList(stats = character.statMap)
    }
}

// TODO: StatButtons composable
// Needs: stat name, stat value, lambdas for increment and decrementing
// Optional: Icon (or Icon resource ID), Modifier

// TODO: TextEntry composable
// Needs: Character info (name, class, description), lambdas for when name, class, and description changes
// Optional: Modifier

// TODO: EnterTextField composable
// Needs: value to display, lambda to handle when the value changes (when user types)
// Optional: String (or String resource id) for label, Icon (or Icon resource ID), Modifier

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