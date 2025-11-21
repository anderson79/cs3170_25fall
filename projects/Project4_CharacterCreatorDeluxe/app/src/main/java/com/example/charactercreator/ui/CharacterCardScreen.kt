package com.example.charactercreator.ui
/**
 * ----------------------------------------------------------------------------
 * Project 4 Starter Code – Character Card Screen (Screen 3)
 * ----------------------------------------------------------------------------
 *
 * This screen displays the FINAL version of the character the user created.
 * It brings together all information entered on:
 *      • The Class Selection screen (name, class, description, image)
 *      • The Talent Point screen (stats → attributes)
 *
 * Your Project 1 assignment (Character Card) is the inspiration for this layout.
 *
 * WHAT THIS FILE PROVIDES:
 *   ✓ The general layout of a character card (title bar, card art, class bar, text block)
 *   ✓ The UI structure using Material3 and your theme
 *   ✓ Placeholder composables (TitleCost, CardType, CardTextStats)
 *   ✓ Callback parameters for Back and Cancel buttons
 *
 * WHAT YOU MUST IMPLEMENT:
 *   • Fill in the actual values for:
 *         - Character name
 *         - Character class
 *         - Description text
 *         - Attack, Defense, Cost (from character.attribMap)
 *
 *   • Add Back and Cancel buttons at the bottom:
 *         - onBackButtonClicked() should return to the previous screen
 *         - onCancelButtonClicked() should reset the character and return to Screen 1
 *
 *   • Customize the look of the card if you want (colors, frames, icons)
 *
 * NOTES:
 *   - This screen should NOT change the character; it only *shows* the result.
 *   - character.attribMap already contains Attack, Defense, and Cost.
 *   - character.characterImage, character.characterColor, and icons are set
 *     in earlier screens or the ViewModel.
 *
 * ----------------------------------------------------------------------------
 */

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.charactercreator.R
import com.example.charactercreator.data.DataSource
import com.example.charactercreator.model.CharacterCreatorUiState
import com.example.charactercreator.ui.theme.CharacterCreatorTheme


@Composable
fun CharacterCardScreen(
    uiState: CharacterCreatorUiState,
    onCancelButtonClicked: () -> Unit,
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    /**
     * uiState.currentCharacter holds ALL final character data.
     * Your job is to DISPLAY it:
     *     - character.name
     *     - character.charClass
     *     - character.description
     *     - character.attribMap["Attack"], ["Defense"], ["Cost"]
     *
     * The placeholder values below ("" and -1) must be replaced with the real data.
     */

    val character = uiState.currentCharacter

    val characterColor = character.characterColor ?: R.color.borderColor
    val cardImageId = character.characterImage ?: R.drawable.ic_launcher_foreground
    val characterIconId = character.characterIcon ?: R.drawable.ic_android_black_24dp
    val classIconId = character.classIcon ?: R.drawable.baseline_smartphone_24

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(colorResource(id = characterColor))
            .fillMaxHeight()
    ) {
        // --------------------------------------------------------------------
        // TITLE + COST ROW
        // --------------------------------------------------------------------
        // TODO: Replace title = "" with the character's name
        // TODO: Replace cost = -1 with the Cost attribute from attribMap
        //
        // Example:
        //     title = character.name
        //     cost = character.attribMap["Cost"] ?: 0
        //
        // The icon and color are already handled for you.
        // --------------------------------------------------------------------

        TitleCost(
            title = "", // TODO
            cost = -1, // TDOO
            characterIcon = characterIconId,
            characterColor = characterColor,
            modifier = Modifier
                .padding(4.dp)
        )

        // This should work without any modification
        Image(
            painter = painterResource(id = cardImageId),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .border(BorderStroke(1.dp, Color.Black))
        )

        // --------------------------------------------------------------------
        // CLASS TYPE ROW
        // --------------------------------------------------------------------
        // TODO: Replace type = "" with character.charClass
        //
        // You may keep the icon as classIconId or swap it with your own.
        //
        // This section mirrors the "type line" from your Project 1 card.
        // --------------------------------------------------------------------
        CardType(
            type = "",
            iconId = classIconId,
            characterColor = characterColor,
            modifier = Modifier
                .padding(4.dp)
        )

        // --------------------------------------------------------------------
        // DESCRIPTION + ATTACK/DEFENSE
        // --------------------------------------------------------------------
        // TODO:
        //   • Replace cardText = "" with character.description
        //   • Replace attack = -1 with attribMap["Attack"]
        //   • Replace defense = -1 with attribMap["Defense"]
        //
        // Remember:
        //   character.attribMap = mapOf("Attack" to ..., "Defense" to ..., "Cost" to ...)
        //
        // This is similar to the bottom text box from Project 1.
        // --------------------------------------------------------------------
        CardTextStats(
            cardText = "", // TODO
            attack = -1, // TODO
            defense = -1, // TODO
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )

        // --------------------------------------------------------------------
        // NAVIGATION BUTTONS
        // --------------------------------------------------------------------
        // TODO: Add Back and Cancel buttons here.
        //
        // Back:
        //     - Goes to the Talent Points screen
        //     - Calls onBackButtonClicked()
        //
        // Cancel:
        //     - Resets the character AND returns to the first screen
        //     - Calls onCancelButtonClicked()
        //
        // You can use Row + OutlinedButton/Button just like previous screens.
        // --------------------------------------------------------------------
    }
}

@Composable
fun TitleCost(
    title: String,
    cost: Int,
    @DrawableRes characterIcon: Int,
    @ColorRes characterColor: Int,
    modifier: Modifier = Modifier
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
            .border(BorderStroke(1.dp, Color.Black))
            .fillMaxWidth()
            .padding(vertical = 4.dp)

    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier
                .padding(start = 8.dp)
        )
        Row(
            modifier = Modifier
                .padding(end = 8.dp)
        ) {

            Text(
                text = "$cost",
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontSize = 18.sp,
            )

            Icon(
                painter = painterResource(characterIcon),
                contentDescription = "",
                tint = colorResource(id = characterColor)
                //Icons.TwoTone.Warning, ""
            )
        }

    }
}

@Composable
fun CardType(
    type: String,
    @DrawableRes iconId: Int,
    @ColorRes characterColor: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.Black))
    ) {
        Text(
            text = type,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.padding(start = 8.dp)
        )
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "",
            tint = colorResource(id = characterColor),
            modifier = Modifier.padding(end = 8.dp, top = 4.dp, bottom = 4.dp)
        )
    }
}

@Composable
fun CardTextStats(
    cardText: String,
    attack: Int,
    defense: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
            .border(BorderStroke(1.dp, Color.Black))
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = cardText,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier
                .padding(start = 8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Text(
                text = "$attack/$defense",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                //textAlign = TextAlign.End
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CharacterCreatorTheme(darkTheme = true) {
        CharacterCardScreen(
            uiState = CharacterCreatorUiState(
                currentCharacter = DataSource.dummyChar
            ),
            onCancelButtonClicked = {},
            onBackButtonClicked = {},
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    }
}