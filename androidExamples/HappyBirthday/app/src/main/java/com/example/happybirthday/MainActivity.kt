/*
HappyBirthday app
CS 3170
In-class Example

The HappyBirthday app demonstrates several basic features of Jetpack Compose
such as simple text formatting, using layouts to create Rows and Columns of
Composables, adding images and strings from the resource manager, and adding
padding with a Modifier
 */
package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingText(
                        message = "Happy Birthday Paris!",
                        from = "From James",
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()

                    )
                }
            }
        }
    }
}

/**
 * GreetingText shows the message and from strings in a Column
 */
@Composable
fun GreetingText(
    message: String,
    from: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly, // you can play with different arrangements
        modifier = modifier
            .padding(8.dp)
    ) {
        // Happy Birthday ...
        Text(
            text = message,
            fontSize = 100.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center
        )


        // from ...
        Text(
            text = from,
            fontSize = 36.sp,
            textAlign = TextAlign.Right, // this only aligns the text to the right within its box
            modifier = Modifier
                .padding(8.dp)
                .align(alignment = Alignment.End) // this aligns the entire text composable to the right
        )
    }
}

@Preview(showBackground = false)
@Composable
fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        GreetingText(
            message = "Happy Birthday James!",
            from = "From Paris",
            modifier = Modifier.fillMaxSize() // unless I pass fillMaxSize(), the app only takes up the minimum amount of space required
        )
    }
}
