package com.example.charactercreator

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.charactercreator.data.DataSource.defaultCharacters
import com.example.charactercreator.model.Character


@Composable
fun TextEntry(
    character: Character,
    onNameChange: (String) -> Unit,
    onClassChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onSelectedClassChange: (String) -> Unit,
    isCustom: Boolean,
    modifier: Modifier = Modifier,
    useDropdown: Boolean = true
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        // enter char name
        Text(
            text = "Enter your character info:"
        )
        EnterTextField(
            inputText = character.name,
            onValueChange = onNameChange,
            labelId = R.string.label_name,
            iconId = R.drawable.outline_person_4_24,
            modifier = Modifier.fillMaxWidth()
        )


        // enter char class
        // TextField version
        if (!useDropdown) {
            EnterTextField(
                inputText = character.charClass,
                onValueChange = onClassChange,
                labelId = R.string.label_class,
                iconId = R.drawable.outline_category_24,
                modifier = Modifier.fillMaxWidth()
            )
        }
        // Dropdown version
        //enter char class
        ClassDropdownMenu(
            currentClass = character.charClass,
            onTextChange = { onClassChange(it) },
            onDropdownChange = { onSelectedClassChange(it) },
            isCustom = isCustom,
        )

        // enter char description
        EnterTextField(
            inputText = character.description,
            onValueChange = onDescriptionChange,
            labelId = R.string.label_description,
            iconId = R.drawable.outline_article_person_24,
            modifier = Modifier.fillMaxWidth()
        )


        // optional: Image button or placeholder
    }
}


/**
 * EnterTextField is just a simplified TextField. It needs a string ID for the label
 * and a drawable resource ID for the icon. This is just to have a little composable
 * where I don't need to always put the code to get the stringResource or drawbleResource
 */
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
        onValueChange = onValueChange,   // newInput is the parameter passed
        label = { Text(stringResource(id = labelId)) },//,
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
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    val classOptions = defaultCharacters.map { it.charClass } + "Custom"

    Column(
        modifier = modifier
    ) {
        TextField(
            value = currentClass,
            onValueChange = { onTextChange(it) },
            label = {
                if (isCustom) {
                    Text(text = "Enter Custom Class")
                } else {
                    Text(text = "Class")
                } // make this dynamic?
            },
            leadingIcon = {
                IconButton(
                    onClick = {
                        expanded = !expanded
                    }) {
                    Icon(
                        imageVector =
                            if (expanded) {
                                Icons.Default.KeyboardArrowUp
                            } else {
                                Icons.Default.KeyboardArrowDown
                            },
                        contentDescription = "Select Class"
                    )
                }
            },
            readOnly = !isCustom,
            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            classOptions.forEach { classOption ->
                DropdownMenuItem(
                    text = { Text(text = classOption) },
                    onClick = {
                        onDropdownChange(classOption)
                        expanded = false
                    }
                )
            }
        }
    }

}


@Preview
@Composable
fun TextEntryPreview() {
    TextEntry(
        character = Character(),
        onNameChange = { } ,
        onClassChange = {  },
        onDescriptionChange = {  },
        onSelectedClassChange = { },
        isCustom = false
    )
}