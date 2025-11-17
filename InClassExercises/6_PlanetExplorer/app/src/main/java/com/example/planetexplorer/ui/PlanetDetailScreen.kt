package com.example.planetexplorer.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.example.planetexplorer.data.Planet
import com.example.planetexplorer.data.planets
import com.example.planetexplorer.ui.theme.LargePillShape
import com.example.planetexplorer.ui.theme.PillShape
import com.example.planetexplorer.ui.theme.PlanetExplorerTheme

/**
 * TODO you will need to pass the planetId of the current selected planet
 * and add arguments for the callbacks for clicking the back button, swiping
 * back (they both go back...) and tapping on the planet image
 *
 * @param planetId
 */
@Composable
fun PlanetDetailScreen(
    planetId: Int,
) {
    val planet = planets.firstOrNull { it.id == planetId } ?: return
    val planetColors = extractPlanetColors(planet)
    val backgroundColor = softPlanetBackground(planetColors.background)

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(color = backgroundColor)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    if (change.position.x < 30.dp.toPx() && dragAmount > 20) {
                        /* TODO */
                    }
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = planet.imageRes),
            contentDescription = planet.name,
            modifier = Modifier
                .size(200.dp)
                .clickable(onClick = {  /* TODO */ })
        )

        Card(
            colors = CardDefaults.cardColors(
                containerColor = planetColors.background,
                contentColor = planetColors.text
            ),
            shape = LargePillShape,
            elevation = CardDefaults.cardElevation(6.dp),
            modifier = Modifier.padding(bottom = 16.dp, start = 8.dp, end = 8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(8.dp)
            ) {
                Spacer(Modifier.height(16.dp))
                Text(planet.name, style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(8.dp))
                Text(
                    planet.description,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(16.dp))
            }

        }
        // Info Section
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            InfoRow(
                label = "Diameter",
                value = "${planet.diameterKm} km",
                planet = planet
            )
            InfoRow(
                label = "Composition",
                value = planet.composition,
                planet = planet
            )
            InfoRow(
                label = "Length of Day",
                value = "${planet.dayLengthHours} hours",
                planet = planet
            )
            InfoRow(
                label = "Length of Year",
                value = "${planet.yearLengthDays} Earth days",
                planet = planet
            )
            InfoRow(
                label = "Moons",
                value = if (planet.moons.isEmpty()) "None" else planet.moons.joinToString(", "),
                planet = planet
            )
        }

        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {  /* TODO */ },
            colors = ButtonDefaults.buttonColors(containerColor = planetColors.background)
        ) {
            Text("Back to List", color = planetColors.text)
        }
    }
}

/**
 * Card that shows
 * Label: value
 *
 * @param label
 * @param value
 * @param planet
 * @param modifier
 */
@Composable
private fun InfoRow(
    label: String,
    value: String,
    planet: Planet,
    modifier: Modifier = Modifier
) {
    val planetColors = extractPlanetColors(planet)

    Card(
        colors = CardDefaults.cardColors(
            containerColor = planetColors.background,
            contentColor = planetColors.text
        ),
        shape = PillShape,
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 32.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(label, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
            Text(value, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
        }
    }
}

/**
 * Helper function to get a nice background color from the planet color
 *
 * @param color
 * @return
 */
fun softPlanetBackground(color: Color): Color {
    // Blend 85% white or 85% black depending on luminance
    val isDark = ColorUtils.calculateLuminance(color.toArgb()) < 0.5
    val blendTarget = if (isDark) Color.White else Color.Black

    return Color(
        ColorUtils.blendARGB(
            blendTarget.toArgb(),
            color.toArgb(),
            0.15f    // Strength of planet tint (0.1–0.2 works really well)
        )
    )
}

@Preview
@Composable
fun PlanetDetailPreview() {
    PlanetExplorerTheme {
        PlanetDetailScreen(
            planetId = 2,
        )
    }
}