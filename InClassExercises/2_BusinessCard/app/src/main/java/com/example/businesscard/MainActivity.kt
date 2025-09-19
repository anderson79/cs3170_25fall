package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusinessCardApp(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCardApp(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.background(color = colorResource(id = R.color.android_green))
    ) {
        // image, name, title
        ImageNameTitleColumn(
            nameId = R.string.name,
            titleId = R.string.title
        )
        // number, handle, email
        // myrow(numberIconId, numberId)
        // myrow(handleIconId, handleId)
        // myrow(emailIconId, emailId)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            IconTextRow(
                icon = Icons.Default.Call,
                textId = R.string.number
            )
            IconTextRow(
                icon = Icons.Default.LocationOn,
                textId = R.string.social
            )
            IconTextRow(
                icon = Icons.Default.Email,
                textId = R.string.email
            )
        }
    }
}

@Composable
fun IconTextRow(
    icon: ImageVector,
    textId: Int,
    modifier: Modifier = Modifier
) {
    Row() {
        //icon
        Icon(
            imageVector = icon,
            contentDescription = null
        )
        // text
        Text(
            text = stringResource(id = textId)
        )
    }
}

@Composable
fun ImageNameTitleColumn(
    nameId: Int,
    titleId: Int,
    modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.background(color = Color.LightGray)
    ) {
        // image
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = "Android Logo",
            modifier = Modifier.size(170.dp)
            //contentScale = ContentScale.Crop
        )

        // name
        Text(
            text = stringResource(id = nameId),
            fontSize = 36.sp,
            modifier = Modifier.padding(horizontal = 9.dp)
        )
        // title
        Text(
            text = stringResource(id = titleId),
            fontSize = 24.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        BusinessCardApp(
            modifier = Modifier.fillMaxSize()
        )
    }
}