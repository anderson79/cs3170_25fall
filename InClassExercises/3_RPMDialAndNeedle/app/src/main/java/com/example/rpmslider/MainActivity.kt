package com.example.rpmslider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    val angle = sliderPosition * 270f

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        DialWithNeedle(angle)
        Spacer(modifier = Modifier.height(24.dp))
        RPMSlider(
            sliderPosition = sliderPosition,
            onValueChange = {newPos -> sliderPosition = newPos}
        )
    }
}

@Composable
fun RPMSlider(
    sliderPosition: Float,
    onValueChange: (Float)->Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Slider(
            value = sliderPosition,
            onValueChange = onValueChange
        )
        //Text(text = sliderPosition.toString())
    }
}

@Composable
fun DialWithNeedle(
    angle: Float
) {
    Box()
    {
        Image(
            painter = painterResource(id = R.drawable.iu),
            contentDescription = "dial"
        )
        Image(
            painter = painterResource(id = R.drawable.rpmneedle),
            contentDescription = "needle",
            modifier = Modifier.rotate(angle)
        )
    }
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
