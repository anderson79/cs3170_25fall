package com.example.charactercreator.ui.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.charactercreator.R
import com.example.charactercreator.data.DataSource.defaultCharacters
import com.example.charactercreator.model.Character


@Composable
fun TextEntry(
    character: Character,
    onNameChange: (String) -> Unit,
    onClassChange: (String) -> Unit,
    onSelectedClassChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    isCustom: Boolean,
    isDefault: Boolean,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        // enter char name
        Text(
            text = "Enter your character info:", modifier = Modifier.padding(4.dp)
        )
        EnterTextField(
            inputText = character.name,
            onValueChange = onNameChange,
            labelId = R.string.label_name,
            iconId = R.drawable.outline_person_4_24,
            modifier = Modifier.fillMaxWidth()
        )


        //enter char class
        ClassDropdownMenu(
            currentClass = character.charClass,
            onTextChange = { onClassChange(it) },
            onDropdownChange = { onSelectedClassChange(it) },
            isCustom = false, // TODO if you want to enable custom character classes
            isDefault = true // TODO if you want to let user make non-defualt characters
        )

        // enter char description
        EnterTextField(
            inputText = character.description,
            onValueChange = {onDescriptionChange },
            labelId = R.string.label_description,
            iconId = R.drawable.outline_article_person_24,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun EnterTextField(
    inputText: String,
    onValueChange: (String) -> Unit,
    @StringRes labelId: Int,
    @DrawableRes iconId: Int,
    modifier: Modifier = Modifier
) {
    TextField(
        value = inputText,
        onValueChange = onValueChange,
        label = { Text(stringResource(id = labelId)) },
        leadingIcon = {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = null
            )
        },
        maxLines = 2,
        modifier = modifier
    )
}

@Composable
fun ClassDropdownMenu(
    currentClass: String,
    onTextChange: (String) -> Unit,
    onDropdownChange: (String) -> Unit,
    isCustom: Boolean,
    isDefault: Boolean,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

      Column(
        modifier = modifier
    ) {
        TextField(
            value = currentClass,
            onValueChange = onTextChange,
            label = { Text("Class") },
            leadingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) {
                            Icons.Default.KeyboardArrowUp
                        } else {
                            Icons.Default.KeyboardArrowDown
                        }, contentDescription = "Select Class"
                    )
                }
            },
            readOnly = false, // TODO if you want user to be able to input text, you need some logic that makes this false
            // for instance, if the character is not default, or if the character is a custom character
            // for me, it was !isCustom && isDefault
            // this meant that on the initial screen, I set isCustom and isDefault to false, because no character
            // had been selected yet. Then once they selected a character from the default list or "Custom"
            // those are updated

            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            /*TODO: use the list of class names to have all the class options in the menu */
            // The DropdownMenu item should go in a loop, the Text should be the class string
            defaultCharacters.forEach { character ->
                DropdownMenuItem(
                    text = { Text(text = character.charClass) },
                    onClick = {
                        onDropdownChange(character.charClass)
                        expanded = false
                    })
            }
        }
    }

}


@Preview
@Composable
fun TextEntryPreview() {
    TextEntry(
        character = Character(
            name = "James", charClass = "Awesome"
        ),
        onNameChange = {},
        onClassChange = {},
        onDescriptionChange = {},
        onSelectedClassChange = {},
        isCustom = false,
        isDefault = false,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    )
}