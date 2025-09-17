package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusinessCardApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCardApp(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
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
    }
}

@Composable
fun IconTextRow(
    iconId: Int,
    textId: Int,
    modifier: Modifier = Modifier
) {
    Row() {
        //icon
        Icon(
            painter = painterResource(id = R.drawable.outline_add_call_24),
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
        modifier = modifier.background(color = Color.LightGray)
    ) {
        // image
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = "Android Logo"
        )

        // name
        Text(
            text = stringResource(id = nameId)
        )
        // title
        Text(
            text = stringResource(id = titleId)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        BusinessCardApp()
//        IconTextRow(
//            iconId = 0,
//            textId = R.string.number
//        )
    }
}