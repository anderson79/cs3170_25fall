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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.charactercreator.data.DataSource.statList
import com.example.charactercreator.model.CharacterCreatorViewModel
import com.example.charactercreator.model.rememberCharacterState
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
    characterCreatorViewModel: CharacterCreatorViewModel = viewModel()
) {

    val characterState = characterCreatorViewModel.uiState.collectAsState()
    val character = characterState.value.currentCharacter
    val remainingPoints = character.maxPoints - character.totalPointsMap
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
                characterCreatorViewModel.onNameChange(newName)
            },
            onClassChange = { newClass ->
                characterCreatorViewModel.onClassChange(newClass)
            },
            onDescriptionChange = { newDesc ->
                characterCreatorViewModel.onDescriptionChange(newDesc)
            },
            onSelectedClassChange = { newSelectedClass ->
                characterCreatorViewModel.onSelectedClassChange(newSelectedClass)
            },
            isCustom = characterState.value.isCustom,
            modifier = Modifier.padding(24.dp)
        )
        Spacer(modifier = Modifier.padding(16.dp))

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
                            characterCreatorViewModel.updateStat(statName = stat.name, delta = 1, maxPoints = character.maxPoints)
                        },
                        onMinusClick = {
                            characterCreatorViewModel.updateStat(statName = stat.name, delta = -1, maxPoints = character.maxPoints)

                        },
                    )
                }

            }
        }

        Spacer(Modifier.padding(vertical = 8.dp))
        Text(text = "Points remaining: ${remainingPoints}/${character.maxPoints}")
        Spacer(Modifier.padding(vertical = 8.dp))

        AttributeList(stats = character.statMap)
    }
}


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