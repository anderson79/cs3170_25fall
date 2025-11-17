package com.example.planetexplorer.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.planetexplorer.data.planets
import com.example.planetexplorer.ui.theme.PlanetExplorerTheme

/**
 * TODO You will need to pass the Id for the current planet, as well
 * as callbacks for the back button and swiping back (they both go back...)
 *
 * @param planetId
 */
@Composable
fun PlanetZoomScreen(
    planetId: Int,
) {
    val planet = planets[planetId]

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    val startX = change.position.x
                    if (startX < 30.dp.toPx() && dragAmount > 20) {
                        /* TODO */
                    }
                }
            }
    ) {

        ZoomableImage(
            imageId = planet.imageRes,
            contentDescription = planet.name
        )

        Button(
            onClick = {/* TODO */ },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Text("Back")
        }
    }
}

/**
 * Display an image of the planet that you can zoom with pinch gestures
 *
 * @param imageId
 * @param contentDescription
 * @param modifier
 */
@Composable
fun ZoomableImage(
    @DrawableRes imageId: Int,
    contentDescription: String,
    modifier: Modifier = Modifier
) {

    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale *= zoom
                    offset += pan
                }
            }
    )
    {
        Image(
            painter = painterResource(imageId),
            contentDescription = contentDescription,
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offset.x,
                    translationY = offset.y
                )
        )
    }
}

@Preview
@Composable
fun ZoomScreenPreview() {
    PlanetExplorerTheme {
        PlanetZoomScreen(
            planetId = 2,
        )
    }
}
