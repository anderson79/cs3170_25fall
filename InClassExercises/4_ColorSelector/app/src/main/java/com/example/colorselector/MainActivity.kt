package com.example.colorselector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.colorselector.ui.theme.ColorSelectorTheme

data class ColorChoice(
    var name: String,
    var color: Color,
    val isFavorite: Boolean = false
) {
    fun setColorChannel(channel: String, value: Float) {
        this.color = when (channel) {
            "Red" -> color.copy(red = value)
            "Green" -> color.copy(green = value)
            "Blue" -> color.copy(blue = value)
            else -> color
        }
    }
}

fun colorFromString(colorStr: String): Color {
    return when (colorStr) {
        "Red" -> Color.Red
        "Green" -> Color.Green
        "Blue" -> Color.Blue
        else -> Color.Unspecified
    }
}

fun colorFromString(colorStr: String, color: Color): Float {
    return when (colorStr) {
        "Red" -> color.red
        "Green" -> color.green
        "Blue" -> color.blue
        else -> 0f
    }
}

val colorOptions = listOf("Red", "Green", "Blue")


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColorSelectorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ColorSelectorApp(
                        myModifier = Modifier.fillMaxSize().padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun ColorSelectorApp(myModifier: Modifier = Modifier) {
    val useDropdown by remember { mutableStateOf(true) }
    val colorState = remember { mutableStateOf(ColorChoice("Red", Color.Red, isFavorite = true)) }
    val currentColor =
        colorState.value  // just a convenient way to access but not change the current color

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = myModifier
            .background(color = currentColor.color)
            .padding(16.dp)
    ) {
        Text("currentColor.isFavorite: ${currentColor.isFavorite}")

        ColorDropdownMenu(
            menuItemClick = {colorOption: String ->
                colorState.value = colorState.value.copy(
                name = colorOption,
                color = colorFromString(colorOption))
            }
        )

        Row() {
            colorOptions.forEach { colorOption ->
                // single radio button
                Row(
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text(text = "$colorOption")

                    RadioButton(
                        selected = currentColor.name == colorOption,
                        onClick = {
                            colorState.value = colorState.value.copy(
                                name = colorOption,
                                color = colorFromString(colorOption)
                                //colorState.value = ColorChoice(name = colorOption, color = colorFromString(colorOption)
                            )

                            // setting the fields directly doesn't trigger recomposition
                            // so the values of name and color change, but the change isn't reflected in the app
                            // colorState.value.name = colorOption
                            // colorState.value.color = colorFromString(colorOption)
                        }
                    )
                }
            }

        }

        ColorSliders(
            color = currentColor.color,
            updateColor = {colorName, newValue-> }

        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorSliders(
    color: Color,
    updateColor: (colorName: String, slider: Float) -> Unit,
    modifier: Modifier = Modifier
) {

    Column() {
        colorOptions.forEach { colorOption ->
            Row() {
                Slider(
                    value = color.red,
                    onValueChange = {}
                )
            }

        }
    }

}

@Composable
fun ColorDropdownMenu(
    menuItemClick: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            //Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            colorOptions.forEach { colorOption ->
                DropdownMenuItem(
                    text = { Text(colorOption) },
                    onClick = {
                        menuItemClick(colorOption)
                        expanded = false
                    }
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ColorSelectorTheme {
        ColorSelectorApp(
            myModifier = Modifier.fillMaxSize()
        )
    }
}