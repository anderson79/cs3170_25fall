package com.example.planetexplorer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.planetexplorer.data.Planet
import com.example.planetexplorer.data.planets
import com.example.planetexplorer.ui.theme.PillShape
import com.example.planetexplorer.ui.theme.PlanetExplorerTheme


/**
 * TODO You will need to pass lambdas into PlanetListScreen
 * that will handle when a planet Card is clicked
 *
 * @param modifier
 */
@Composable
fun PlanetListScreen(
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier
    ) {
        items(planets) {
            planet ->
            PlanetCard(
                planet = planet,
                onCardClick = {  /* TODO */  },
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

/**
 * Display a planet image and name on a card, PlanetCard uses
 * extractPlanetColors to determine the color of the card and text
 *
 * @param planet
 * @param onCardClick
 * @param modifier
 */
@Composable
fun PlanetCard(
    planet: Planet,
    onCardClick: () -> Unit,
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
        modifier = modifier.clickable(
            enabled = true,
            onClick = onCardClick
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(height = 102.dp)
                .fillMaxWidth()
        ){
            Image(
                painter = painterResource(id = planet.imageRes),
                contentDescription = planet.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(84.dp)
)
            Text(text = planet.name,
                fontSize = 68.sp,
                modifier = Modifier.padding(start = 24.dp))
        }
    }

}



@Preview
@Composable
fun PlanetCardPreview()
{
    PlanetExplorerTheme {
        PlanetCard(
            planet = planets.get(1),
            onCardClick = {}
        )
    }
}

@Preview
@Composable
fun PlanetListPreview()
{
    PlanetExplorerTheme {
       PlanetListScreen()
    }
}