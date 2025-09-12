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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
                        // if you right-click on a string, you can select Extract String Resource
                        message = stringResource( id = R.string.happy_birthday_paris),
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

@Composable
fun GreetingImage(
    message: String,
    from: String,
    modifier: Modifier = Modifier
) {
    val imageID = R.drawable.paris_pic  // get the ID of our image
    val painterRes = painterResource(id = imageID)  // create a Painter from the image ID

    Box(
        modifier = modifier
            .background(color = Color.Magenta), // this will make the background of the box magenta
                                                // anything we draw inside the box will cover it up
                                                // unless we allow the magenta through...
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterRes, // pass the painter from above to be used as the imge
            contentDescription = "",    // content description is required for Image
            contentScale = ContentScale.Crop,   // crop the image to fill the screen
            alpha = 0.6f // if we turn the alpha down some of the background will come through
        )

        GreetingText(
            message = message,
            from = from
        )
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
        //horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxSize()
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
        GreetingImage(
            message = "Happy Birthday James!",
            from = "From Paris",
            modifier = Modifier.fillMaxSize() // unless I pass fillMaxSize(), the app only takes
                                                // up the minimum amount of space required
        )
    }
}
