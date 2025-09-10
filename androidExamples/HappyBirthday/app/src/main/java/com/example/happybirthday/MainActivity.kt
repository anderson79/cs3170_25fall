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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                }
            }
        }
    }
}

@Composable
fun GreetingText(
    message: String,
    from: String,
    modifier: Modifier = Modifier
) {
    // Happy Birthday to...
    Text(
        text = message,
        fontSize = 100.sp,
        lineHeight = 116.sp
    )

    // from...
    Text(
        text = from,
        fontSize = 36.sp,
    )

}

@Preview(showBackground = false)
@Composable
fun BirthdayCardPreview() {
    GreetingText(
        message = "Happy Birthday James!",
        from = "From Paris"
    )
}
