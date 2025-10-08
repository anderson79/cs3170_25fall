package com.example.rpmslider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rpmslider.ui.theme.RPMSliderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RPMSliderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RPMSliderApp(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun RPMSliderApp(modifier: Modifier = Modifier) {

}

@Composable
fun RPMSlider(
) {

}

@Composable
fun DialWithNeedle(
) {

}


@Preview(showBackground = true)
@Composable
fun RPMSliderPreview() {
    RPMSliderTheme {
        RPMSliderApp(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}
