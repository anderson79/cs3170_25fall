package com.example.charactercard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.charactercard.ui.theme.CharacterCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CharacterCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        TitleCost(
            title = "Paris",
            cost = "123",
            icon = Icons.Default.Email
        )
        TitleCost(
            title = "James",
            cost = "321",
            icon = Icons.Default.LocationOn
        )
        TitleCost(
            title = "Tom",
            cost = "456",
            icon = Icons.Default.Email
        )
        TitleCost(
            title = "Matt",
            cost = "",
            icon = Icons.Default.Email
        )
    }
}

@Composable
fun TitleCost(
    title: String,
    cost: String,
    icon: ImageVector
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(width = 3.dp, color = Color.Red)

    ) {
        Text(
            title, fontSize = 50.sp,
                modifier = Modifier.padding(8.dp)
            )
        NumberIcon(cost, icon)
    }
}

@Composable
fun NumberIcon(
    cost: String,
    icon: ImageVector
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.border(width = 3.dp, color = Color.Green)
    ){
        Text(cost, fontSize = 50.sp)
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(size = 50.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CharacterCardTheme {
        Greeting("Android")
    }
}