package com.example.charactercreator

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource


@Composable
fun TextEntry(
    character: Character,
    onNameChange: (String) -> Unit,
    onClassChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    useDropdown: Boolean = false
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
        //onValueChange = { amountInput = it },
        label = { Text(stringResource(id = labelId)) },//,
        //singleLine = true,
        //keyboardOptions = keyboardOptions,
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
