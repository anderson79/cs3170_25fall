/*
 * The Tip Calculator was created by following the "Interacting with UI and State" slides
 */
package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    TipCalculatorApp()
                }
            }
        }
    }
}


@Composable
fun EditNumberField(
    amountInput: String,
    onValueChange: (String) -> Unit,
    @StringRes labelId: Int,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
) {

    TextField(
        value = amountInput,
        onValueChange = onValueChange,   // newInput is the parameter passed
        //onValueChange = { amountInput = it },
        label = {Text(stringResource(id = labelId))},//,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}


@Composable
fun TipCalculatorApp() {
    var amountInput by remember { mutableStateOf("") }
    var tipPercentInput by remember { mutableStateOf("") }

    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tipPercent = tipPercentInput.toDoubleOrNull() ?: 0.0

    val tipAmount = calculateTip(amount, tipPercent)

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        // number field for amount
        EditNumberField(
            amountInput = amountInput,
            //onValueChange = { newAmount -> amountInput = newAmount },
            onValueChange = { amountInput = it },
            labelId = R.string.bill_amount,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )
        // number field for tip percentage
        EditNumberField(
            amountInput = tipPercentInput,
            onValueChange = { newTipPercent -> tipPercentInput = newTipPercent },
            labelId = R.string.how_was_the_service,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )


        Text(
            text = stringResource(R.string.tip_amount,tipAmount),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(150.dp))
    }
}

/**
 * Calculates the tip based on the user input and format the tip amount
 * according to the local currency.
 * Example would be "$10.00".
 */
private fun calculateTip(amount: Double, tipPercent: Double = 15.0): String {
    val tip = tipPercent / 100 * amount
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun TipCalculatorAppPreview() {
    TipTimeTheme {
        TipCalculatorApp()
    }
}
