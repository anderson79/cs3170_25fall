package com.example.planetexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.planetexplorer.ui.theme.PlanetExplorerTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlanetExplorerTheme {
                PlanetApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetAppBar(
//    canNavigateBack: Boolean, // it's not necessary, but you can implement the
//    navigateUp: () -> Unit, // navigate back from the top bar like in the Cupcake app
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        // if you want to implement navigating back from the app bar, you can uncomment this
//        navigationIcon = {
//            if (canNavigateBack) {
//                IconButton(onClick = navigateUp) {
//                    Icon(
//                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                        contentDescription = stringResource(R.string.back_button)
//                    )
//                }
//            }
//        }
    )
}

@Composable
fun PlanetApp(
    modifier: Modifier = Modifier,
) {

    var currentPlanetId by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            PlanetAppBar(
//                canNavigateBack = false, // placeholders if you want to
//                navigateUp = {}           // try navigating from the top app bar
            )
        }
    ) { innerPadding ->

        // this is where you will create your NavHost and define the routes
        // you can remove this, if you keep it, it will probably appear on each screen.
        // Or you can make it a hidden easter egg...
        Box(
            modifier = modifier.padding(innerPadding)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(R.string.sagan_quote),
                    fontSize = 24.sp,
                )
                Text(
                    text = stringResource(R.string.carl_sagan),
                    textAlign = TextAlign.End,
                    fontSize = 24.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PlanetExplorerTheme(
        darkTheme = true
    ) {
        PlanetApp(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}
