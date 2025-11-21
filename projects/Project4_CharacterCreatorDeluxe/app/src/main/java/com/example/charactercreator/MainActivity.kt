package com.example.charactercreator

/**
 * ----------------------------------------------------------------------------
 * Project 4 Starter Code – MainActivity & Navigation Skeleton
 * ----------------------------------------------------------------------------
 *
 * This file provides the *navigation structure* for the Character Creator app.
 * You will NOT finish the project in this file alone, but it gives you:
 *
 *   • A NavHost that defines the three screens:
 *          1. Class Selection
 *          2. Talent Point Spending
 *          3. Character Card
 *
 *   • A top app bar that automatically updates as you navigate
 *   • Empty callbacks (TODOs) where YOU will connect the UI to your ViewModel
 *   • An enum (CharacterCreatorScreen) representing your app's screens
 *
 * IMPORTANT:
 *   - All of the “real logic” has been removed so that YOU implement it.
 *   - Anywhere you see a TODO, you must fill in navigation or state updates.
 *   - Review how we navigated in the Cupcake app:
 *         navController.navigate("SomeRoute")
 *         navController.popBackStack()
 *
 * You are expected to:
 *   ✓ Connect text fields and dropdowns to your ViewModel
 *   ✓ Navigate between screens when buttons are pressed
 *   ✓ Pass updated character data to each screen
 *   ✓ Reset the app when the Reset/Cancel buttons are pressed
 *
 * Treat this file as your "wiring diagram" for the whole app.
 * ----------------------------------------------------------------------------
 */

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.charactercreator.model.CharacterCreatorViewModel
import com.example.charactercreator.ui.CharacterCardScreen
import com.example.charactercreator.ui.ClassSelectionScreen
import com.example.charactercreator.ui.TalentPointScreen
import com.example.charactercreator.ui.theme.CharacterCreatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CharacterCreatorTheme {
                CharacterCreatorApp()
            }
        }
    }
}

/**
 * This enum lists all screens in the app and provides each screen’s title.
 *
 * The route name used in navigation is simply the enum's name, e.g.:
 *      CharacterCreatorScreen.ClassSelect.name  →  "ClassSelect"
 *
 * You will use these routes when calling navController.navigate().
 */
enum class CharacterCreatorScreen(@StringRes val title: Int) {
    ClassSelect(title = R.string.select_class),
    SpendTalents(title = R.string.spend_talents),
    Card(title = R.string.card),
    AppName(title = R.string.app_name)
}


/**
 * Top bar used on all screens.
 *
 * - Automatically shows the name of the current screen
 * - Shows a Back button ONLY when navigation history exists
 *
 * You don't need to modify this, but understanding it helps you make
 * Compose apps that use consistent UI chrome across screens.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterCreatorTopBar(
    currentScreen: CharacterCreatorScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                stringResource(id = currentScreen.title),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

/**
 * ----------------------------------------------------------------------------
 * MAIN APP COMPOSABLE
 * ----------------------------------------------------------------------------
 * This is the central "orchestra conductor" for the whole app.
 *
 * It does three main things:
 *   1. Creates the NavController (required for navigation)
 *   2. Gets the current UI state from your ViewModel
 *   3. Defines all navigation routes via NavHost
 *
 * YOU are responsible for:
 *   ✓ Filling in the TODOs inside each composable block
 *   ✓ Adding navigation actions (navigate, popBackStack)
 *   ✓ Connecting UI events to ViewModel functions
 *
 * Nothing inside the NavHost is complete—you MUST finish it.
 * ----------------------------------------------------------------------------
 */
@Composable
fun CharacterCreatorApp(
    modifier: Modifier = Modifier,
    viewModel: CharacterCreatorViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = CharacterCreatorScreen.valueOf(
        backStackEntry?.destination?.route ?: CharacterCreatorScreen.AppName.name
    )

    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CharacterCreatorTopBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() })
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = CharacterCreatorScreen.ClassSelect.name,
            modifier = modifier.padding(innerPadding)
        ) {
            /**
             * ----------------------------------------------------------------------------
             * NAVIGATION ROUTES
             * ----------------------------------------------------------------------------
             * Each composable route corresponds to a screen in your app.
             *
             * The UI for each screen is already built in separate files, but:
             *    YOU must connect the buttons and input fields to logic here.
             *
             * Example:
             *     onNextClick = { navController.navigate("SpendTalents") }
             *
             * Every callback currently says TODO – replace these with:
             *    - Navigation calls
             *    - ViewModel update functions
             *    - Reset logic when needed
             * ----------------------------------------------------------------------------
             */

            // ---------------------------------------------------------------------------
            // CLASS SELECTION SCREEN (Screen 1)
            // Students: implement the callbacks to update character fields
            // and navigate to the Talent Point screen.
            // ---------------------------------------------------------------------------
            composable(route = CharacterCreatorScreen.ClassSelect.name) {
                ClassSelectionScreen(
                    uiState = uiState,
                    onNameChange = {/* TODO */ },
                    onClassChange = { /* TODO */ },
                    onDescriptionChange = { /* TODO */ },
                    onSelectedClassChange = { /* TODO */ },
                    onNextClick = { /* TODO: navigate to SpendTalents */ },
                    onResetClick = {/* TODO */ },
                    isNextEnabled = false // TODO: enable based on validation
                )
            }

            // ---------------------------------------------------------------------------
            // TALENT POINT SCREEN (Screen 2)
            // Students: implement + and – logic (updateStat) and navigation.
            // ---------------------------------------------------------------------------
            composable(route = CharacterCreatorScreen.SpendTalents.name) {
                TalentPointScreen(
                    uiState = uiState,
                    updateStat = { stat: String, delta: Int, maxPoints: Int ->
                        /* TODO */
                    },
                    onNextClick = { /* TODO: Navigate to Card */ },
                    onBackClick = { /* TODO: Navigate back to the previous screen */ },
                    onResetClick = { /* TODO: reset the character and navigate back to ClassSelect */
                    },
                    enableNextButton = false // TODO: enable based on validation
                )
            }

            // ---------------------------------------------------------------------------
            // CHARACTER CARD SCREEN (Screen 3)
            // Students: show final character info, allow Back/Cancel navigation.
            // ---------------------------------------------------------------------------
            composable(route = CharacterCreatorScreen.Card.name) {
                CharacterCardScreen(
                    uiState = uiState,
                    onBackButtonClicked = { /* TODO: navigate back to previous screen */ },
                    onCancelButtonClicked = { /* TODO: reset the character and navigate back to ClassSelect */ }
                )
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun CharacterCreatorPreview() {
    CharacterCreatorTheme {
        CharacterCreatorApp(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        )
    }
}