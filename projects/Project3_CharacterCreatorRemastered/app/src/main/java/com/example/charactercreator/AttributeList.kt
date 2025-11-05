package com.example.charactercreator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AttributeList(
    stats: Map<String, Int>,
    modifier: Modifier = Modifier
) {
    val attributeMap = Character.computeAttributes(stats)
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(horizontal = 64.dp)
    ) {
        items(attribList) { attribute ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(painterResource(id = attribute.icon), contentDescription = "")
                Text(text = "${attribute.name}:", modifier = modifier
                    .fillMaxWidth()
                    .weight(1f))
                Text(text = "${attributeMap.getOrDefault(attribute.name, 0)}")
            }
        }
    }
}