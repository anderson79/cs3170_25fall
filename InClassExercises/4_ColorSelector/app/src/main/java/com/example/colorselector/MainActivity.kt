package com.example.colorselector

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.colorselector.ui.theme.ColorSelectorTheme

data class ColorChoice(
    var name: String,
    var color: Color,
    val isFavorite: Boolean = false
)

fun colorFromString(colorStr: String): Color {
    return when (colorStr) {
        "Red" -> Color.Red
        "Green" -> Color.Green
        "Blue" -> Color.Blue
        else -> Color.Unspecified
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
                    Greeting(
                        myModifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(myModifier: Modifier = Modifier) {
    val colorState = remember { mutableStateOf(ColorChoice("Red", Color.Red, isFavorite = true)) }
    val currentColor = colorState.value  // just a convenient way to access but not change the current color

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = myModifier
            .background(color = currentColor.color)
            .padding(16.dp)
    ){
    Text("currentColor.isFavorite: ${currentColor.isFavorite}")
        Row() {

            // fun foo(colorOption: String)
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
                        colorState.value = currentColor.copy(name = colorOption, color = colorFromString(colorOption))
//                        colorState.value.name = colorOption
                        //colorState.value.color = colorFromString(colorOption)
                    }
                )
            }
        }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ColorSelectorTheme {
        Greeting(
            myModifier = Modifier.fillMaxSize()
        )
    }
}