package com.example.superheroesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroesapp.data.HeroesRepository
import com.example.superheroesapp.data.HeroesRepository.heroes
import com.example.superheroesapp.model.Hero
import com.example.superheroesapp.ui.theme.Shapes
import com.example.superheroesapp.ui.theme.SuperheroesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperheroesAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HeroesList(
                        heroesList = heroes,
                        modifier = Modifier
                            .wrapContentSize()
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HeroesList(
    heroesList: List<Hero>,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.secondary )
    ) {
        items(heroesList) { hero ->
            HeroCard(
                hero = hero,
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun HeroCard(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.primary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .height(72.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(hero.nameRes),
                )
                Text(
                    text = stringResource(hero.descriptionRes)
                )
            }
            Image(
                painter = painterResource(id = hero.imageRes),
                contentDescription = stringResource(hero.nameRes),
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clip(shape = Shapes.small)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeroListPreview() {
    SuperheroesAppTheme(
        darkTheme = false,
        dynamicColor = false
    ) {
         HeroesList(
             heroesList = HeroesRepository.heroes,
             modifier = Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
fun HeroCardPreview() {
    SuperheroesAppTheme {
        val heroNum1 = Hero(
            nameRes = R.string.hero1,
            descriptionRes = R.string.description1,
            imageRes = R.drawable.android_superhero1
        )
        HeroCard(heroNum1)
    }
}