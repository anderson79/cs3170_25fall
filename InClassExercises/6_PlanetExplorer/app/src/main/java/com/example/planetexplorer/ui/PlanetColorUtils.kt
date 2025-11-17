package com.example.planetexplorer.ui

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.imageResource
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import com.example.planetexplorer.data.Planet

private val colorCache = mutableMapOf<Int, PlanetColors>()

@Composable
fun extractPlanetColors(planet: Planet) : PlanetColors {
    colorCache[planet.id]?.let {return it}

    val imageBitmap: ImageBitmap = ImageBitmap.imageResource(id = planet.imageRes)
    val androidBitmap: Bitmap = imageBitmap.asAndroidBitmap()
    val palette: Palette = Palette.from(androidBitmap).generate()
    val dominantSwatch = palette.dominantSwatch
    val planetColor = dominantSwatch?.rgb?.let { Color(it)} ?: Color.Unspecified
    val textColor = if (ColorUtils.calculateLuminance(planetColor.toArgb()) < 0.5) {Color.White} else {Color.Black}

    colorCache[planet.id] = PlanetColors(background = planetColor, text = textColor)
    return colorCache.getOrDefault(key=planet.id, PlanetColors(background = Color.White, text = Color.Black))
}

data class PlanetColors(
    val background: Color,
    val text: Color
)