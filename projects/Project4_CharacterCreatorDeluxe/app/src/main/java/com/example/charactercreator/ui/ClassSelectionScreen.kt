package com.example.charactercreator.ui
/**
 * ----------------------------------------------------------------------------
 * Project 4 Starter Code – Class Selection Screen (Screen 1)
 * ----------------------------------------------------------------------------
 *
 * This is the FIRST screen in the Character Creator Deluxe app.
 * The user will:
 *      • Enter a character name
 *      • Choose a class (default classes OR custom)
 *      • Enter a description
 *      • View the character image associated with the selected class
 *
 * WHAT THIS FILE PROVIDES:
 *   ✓ The overall layout structure (Column)
 *   ✓ A Character object from uiState (uiState.currentCharacter)
 *   ✓ Callback parameters for updating fields and navigating
 *   ✓ A preview you can use to design your UI
 *
 * WHAT YOU MUST IMPLEMENT:
 *   • Add TextFields (or the TextEntry composable) for:
 *        - Name
 *        - Class (Dropdown)
 *        - Description
 *
 *   • Connect those TextFields to the correct callbacks:
 *        onNameChange()
 *        onClassChange()
 *        onSelectedClassChange()
 *        onDescriptionChange()
 *
 *   • Display the selected character image
 *
 *   • Add Reset and Next buttons and connect them to:
 *        onResetClick()
 *        onNextClick()
 *
 * HINTS:
 *   - The dropdown menu is implemented in TextEntry.kt → ClassDropdownMenu()
 *   - The ViewModel will update the character when you call the callbacks.
 *   - This screen should only handle UI; DO NOT compute stats here.
 *
 * ----------------------------------------------------------------------------
 */

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.charactercreator.R
import com.example.charactercreator.data.DataSource
import com.example.charactercreator.model.CharacterCreatorUiState
import com.example.charactercreator.ui.theme.CharacterCreatorTheme
import com.example.charactercreator.ui.util.TextEntry

@Composable
fun ClassSelectionScreen(
    uiState: CharacterCreatorUiState,
    onNameChange: (String) -> Unit,
    onClassChange: (String) -> Unit,
    onSelectedClassChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onResetClick: () -> Unit,
    onNextClick: () -> Unit,
    isNextEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    /**
     * The CharacterCreatorUiState contains the current character information.
     * You will display and modify these fields through the callback functions:
     *    - onNameChange()
     *    - onClassChange()
     *    - onSelectedClassChange()
     *    - onDescriptionChange()
     *
     * NONE of these fields update automatically — you MUST call the callbacks
     * when the user types or selects something.
     */

    val character = uiState.currentCharacter
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = character.characterColor?: R.color.purple_200))
    ) {
        // --------------------------------------------------------------------
        // INPUT FIELDS (Name, Class, Description)
        // --------------------------------------------------------------------
        // You can use the EnterTextField() and ClassDropdownMenu() composables
        // found in TextEntry.kt, OR you can build your own TextFields.
        //
        // REQUIRED:
        //   • Name text field → calls onNameChange()
        //   • Class dropdown → calls onSelectedClassChange() and maybe onClassChange()
        //   • Description field → calls onDescriptionChange()
        //
        // The uiState.currentCharacter contains the *current* values.
        // You must display them and update them through the callback functions.
        // --------------------------------------------------------------------


        // --------------------------------------------------------------------
        // CHARACTER IMAGE
        // --------------------------------------------------------------------
        // Display the image associated with the selected class.
        // The ViewModel sets character.characterImage when a default class is
        // selected. For custom classes, you may choose a placeholder image.
        //
        // Example (after wiring data):
        //    Image(
        //        painter = painterResource(id = character.characterImage ?: R.drawable.placeholder),
        //        ...
        //    )
        //
        // TODO: Implement the Image composable here.
        // --------------------------------------------------------------------


        // --------------------------------------------------------------------
        // NAVIGATION BUTTONS
        // --------------------------------------------------------------------
        // NEXT button:
        //   - Should be enabled only when required text fields are filled in.
        //   - Calls onNextClick() to navigate to the Talent Point screen.
        //
        // RESET button:
        //   - Calls onResetClick()
        //   - Should reset all fields and return to the default blank character.
        //
        // TODO: Add OutlinedButton("Reset") and Button("Next")
        // TODO: Connect them to the callback functions provided.
        // --------------------------------------------------------------------
    }
}

@Preview(showBackground = true)
@Composable
fun ClassSelectionPreview() {
    CharacterCreatorTheme(darkTheme = true) {
        ClassSelectionScreen(
            uiState = DataSource.dummyUiState,
            onNameChange = {},
            onClassChange = {},
            onSelectedClassChange = {},
            onDescriptionChange = {},
            onResetClick = {},
            onNextClick = {},
            isNextEnabled = false
        )
    }
}