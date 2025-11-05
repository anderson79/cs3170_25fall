package com.example.charactercreator

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun StatButtons(
    statName: String,
    value: String,
    @DrawableRes iconId: Int,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        // increment stat
        Button(
            onClick = onPlusClick,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_drop_up_24),
                contentDescription = "$statName up"
            )
        }
        Row {
            Icon(painter = painterResource(id = iconId), contentDescription = statName)
            Text(
                text = statName,
                modifier = modifier.padding(start = 4.dp)
            )
        }

        Text(
            text = value
        )
        Button(onClick = onMinusClick) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_drop_down_24),
                contentDescription = "$statName down"
            )
        }
    }
}