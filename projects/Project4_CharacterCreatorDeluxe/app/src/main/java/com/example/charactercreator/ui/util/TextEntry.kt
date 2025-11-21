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
            inputText = "", // TODO
            onValueChange = {/*TODO*/ },
            labelId = R.string.label_name,
            iconId = R.drawable.outline_person_4_24,
            modifier = Modifier.fillMaxWidth()
        )


        //enter char class
        ClassDropdownMenu(
            currentClass = "", // TODO
            onTextChange = { /*TODO*/ },
            onDropdownChange = { /*TODO*/ },
            isCustom = false, // TODO if you want to enable custom character classes
            isDefault = true // TODO if you want to let user make non-defualt characters
        )

        // enter char description
        EnterTextField(
            inputText = "", // TODO
            onValueChange = {/*TODO*/ },
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
        value = "", // TODO
        onValueChange = {},   // newInput is the parameter passed
        label = { Text(stringResource(id = labelId)) }, leadingIcon = {
            Icon(
                painter = painterResource(id = iconId), contentDescription = null
            )
        }, maxLines = 2, modifier = modifier
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

    // TODO create a list of the class name strings so you can use it below in the DropdownMenu

    Column(
        modifier = modifier
    ) {
        TextField(
            value = "", // TODO
            onValueChange = { /*TODO*/ },
            label = { Text("Class") },
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = if (expanded) {
                            Icons.Default.KeyboardArrowUp
                        } else {
                            Icons.Default.KeyboardArrowDown
                        }, contentDescription = "Select Class"
                    )
                }
            },
            readOnly = true, // TODO if you want user to be able to input text, you need some logic that makes this false
            // for instance, if the character is not default, or if the character is a custom character
            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenu(
            expanded = false, // TODO
            onDismissRequest = { /*TODO */ },
        ) {
            /*TODO: use the list of class names to have all the class options in the menu */
            // The DropdownMenu item should go in a loop, the Text should be the class string
            DropdownMenuItem(
                text = { Text(text = "Class1") },
                onClick = { /*TODO*/ })
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