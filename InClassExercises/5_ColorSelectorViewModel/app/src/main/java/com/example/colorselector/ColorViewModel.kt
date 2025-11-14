package com.example.colorselector

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ColorChoiceUiState(
    val currentColor: ColorChoice,
    //val favoriteColor: String,
    val favoriteColor: Color
)
data class ColorChoice(
    val name: String,
    val color: Color,
    val isFavorite: Boolean = false
) {
    fun setColorChannel(channel: String, value: Float): Color {
        return when (channel) {
            "Red" -> color.copy(red = value)
            "Green" -> color.copy(green = value)
            "Blue" -> color.copy(blue = value)
            else -> color
        }
    }

    fun withUpdatedColor(channel: String, value: Float): ColorChoice {
        val newColor = setColorChannel(channel, value)

        return this.copy(color = newColor)
    }

    fun withUpdatedColorString(colorStr: String) : ColorChoice {
        val newColor = colorFromString(colorStr)

        return this.copy(name = colorStr, color = newColor)
    }
}

/**
 * This function takes a String either "Red" "Green" or "Blue"
 * and returns a color object with only that color
 */
fun colorFromString(colorStr: String): Color {
    return when (colorStr) {
        "Red" -> Color.Red
        "Green" -> Color.Green
        "Blue" -> Color.Blue
        "Magenta" -> Color.Magenta
        else -> Color.Unspecified
    }
}

/**
 * This function lets us pass in a string "Red" "Green" or "Blue" and
 * return the corresponding value of the RGB color component
 *
 */
fun colorFromString(colorStr: String, color: Color): Float {
    return when (colorStr) {
        "Red" -> color.red
        "Green" -> color.green
        "Blue" -> color.blue
        else -> 0f
    }
}

val colorOptions = listOf("Red", "Green", "Blue", "Magenta")

class ColorViewModel: ViewModel() {
    private var _colorState = MutableStateFlow(ColorChoice("Red", Color.Red, isFavorite = true))
    val colorState: StateFlow<ColorChoice> = _colorState


    fun selectColor(name: String) {
        val newColor = colorFromString(name)

         //_colorState.value = _colorState.value.copy(name = name, color = newColor)

        _colorState.update { currentState ->
            //currentState.copy(name = name, color = newColor)
        currentState.withUpdatedColorString(name)

        }
    }

    fun updateChannel(channel: String, value: Float) {
        _colorState.value = _colorState.value.withUpdatedColor(channel, value)
    }
}