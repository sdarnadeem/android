package com.nasyxnadeem.tipcalculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nasyxnadeem.tipcalculator.components.InputField
import com.nasyxnadeem.tipcalculator.ui.theme.TipCalculatorTheme
import com.nasyxnadeem.tipcalculator.utils.calculateTotalPerPerson
import com.nasyxnadeem.tipcalculator.utils.calculateTotalTip
import com.nasyxnadeem.tipcalculator.widgets.RoundIconButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                Column {
                    MainContent()
                }
            }
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(modifier: Modifier = Modifier, onValChange: (String) -> Unit = {}) {
    val totalBillState = remember {
        mutableStateOf("")
    }

    val isValid = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    val sliderPositionState = remember {
        mutableStateOf(0f)
    }

    val splitByState = remember {
        mutableStateOf(1)
    }

    val tipAmountState = remember {
        mutableStateOf(10f)
    }

    val totalPerPerson = remember {
        mutableStateOf(1.0)
    }

    TopHeader(totalPerPerson = totalPerPerson.value)

    Surface(
        modifier = Modifier.padding(2.dp),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(6.dp)
        ) {
            InputField(
                modifier = modifier,
                valueState = totalBillState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!isValid) return@KeyboardActions
                    onValChange(totalBillState.value.trim())
                    keyboardController?.hide()
                })

            if (isValid) {
                Row(modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.Start) {
                    Text(
                        text = "Split",
                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(120.dp))

                    Row(
                        modifier = Modifier.padding(horizontal = 3.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RoundIconButton(
                            imageVector = Icons.Default.Remove,
                            onClick = { if (splitByState.value > 1) splitByState.value--

                                val tipPercentage = (sliderPositionState.value * 100).toInt()

                                totalPerPerson.value = calculateTotalPerPerson(totalBillState.value.toDouble(), splitByState.value, tipPercentage)
                            })
                    }
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .align(alignment = Alignment.CenterVertically),
                        text = splitByState.value.toString()
                    )
                    RoundIconButton(
                        imageVector = Icons.Default.Add,
                        onClick = { splitByState.value++
                            val tipPercentage = (sliderPositionState.value * 100).toInt()

                            totalPerPerson.value = calculateTotalPerPerson(totalBillState.value.toDouble(), splitByState.value, tipPercentage)

                        })


                }

                // Tip Row

                Row(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "Tip",
                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                    )
                    Spacer(
                        modifier = Modifier
                            .width(200.dp)
                            .align(alignment = Alignment.CenterVertically)
                    )
                    Text(text = "$33.00")
                }
                5
                // Slider + text column

                val tipPercentage = (sliderPositionState.value * 100).toInt()
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "$ $tipPercentage")

                    Spacer(modifier = Modifier.height(14.dp))

                    Slider(
                        steps = 10,
                        value = sliderPositionState.value,
                        onValueChange = { newVal ->
                            sliderPositionState.value = newVal
                            calculateTotalTip(
                                totalBill = totalBillState.value.toDouble(),
                                tipPercentage = tipPercentage
                            )

                            totalPerPerson.value = calculateTotalPerPerson(
                                totalBillState.value.toDouble(),
                                splitByState.value,
                                tipPercentage
                            )

                        },
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                    )
                }
            } else {


            }
        }

    }

}


@Preview
@Composable
fun MainContent() {
    BillForm(Modifier.fillMaxWidth()) { billAmt ->
        Log.d("bm", "MainContent: $billAmt")
    }
}

//@Preview
@Composable
fun TopHeader(totalPerPerson: Double = 134.0) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp))), color = Color(0xFFE9D7F7)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val total = "%.2f".format(totalPerPerson)
            Text(text = "Total Per Person", style = MaterialTheme.typography.h5)
            Text(
                text = "$$total",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    TipCalculatorTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}


//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipCalculatorTheme {
        MyApp {
            TopHeader()
        }
    }
}