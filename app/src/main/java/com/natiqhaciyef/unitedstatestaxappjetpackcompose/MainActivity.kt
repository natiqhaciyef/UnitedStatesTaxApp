package com.natiqhaciyef.unitedstatestaxappjetpackcompose

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.natiqhaciyef.unitedstatestaxappjetpackcompose.data.items.StatesNameList
import com.natiqhaciyef.unitedstatestaxappjetpackcompose.data.model.UnitedState
import com.natiqhaciyef.unitedstatestaxappjetpackcompose.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitedStatesTaxAppJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

//@Preview
@Composable
fun HomeScreen() {
    val list = StatesNameList.states
    val selectedOption = remember { mutableStateOf(list[0]) }
    val input = remember { mutableStateOf("0.0") }

    val state = searchState(selectedOption.value)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopView(input = input.value)

            MainBody(
                list = list,
                selectedOption = selectedOption,
                input = input.value,
                mainInput = input,
                state = state
            ) {
                input.value = it
            }

            BottomView(image = state.image)
        }
    }
}

@Composable
fun TopView(input: String) {

    Box {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .padding(20.dp, 20.dp,20.dp,15.dp)
                .clip(RoundedCornerShape(17.dp)),
            color = MaterialTheme.colors.onError
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val totalPerPerson = formatCustomer(input)

                CustomText(
                    text = "Total Price",
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, 0.dp)
                        .fillMaxWidth(),
                    fontSize = 23,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp))

                CustomBillText(
                    text = totalPerPerson,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 0.dp, 0.dp, 5.dp),
                    fontSize = 27,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainBody(
    list: List<String>,
    selectedOption: MutableState<String>,
    input: String,
    state: UnitedState,
    mainInput: MutableState<String>,
    function: (String) -> Unit
) {
    val validState = remember {
        input.trim().isNotEmpty()
    }

    val isChecked = remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 0.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(15.dp)
                .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(10.dp))
                .background(Color.White)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomOutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 15.dp, 10.dp, 0.dp),
                    input = input,
                    onAction = KeyboardActions {
                        if (!validState) return@KeyboardActions

                        keyboardController?.hide()
                    },
                    function = function
                )

                CustomDropDownMenu(list = list, selectedOption = selectedOption)

                val incomeTax = formatCustomer(state.incomeTax.toString())
                val salesTax = formatCustomer(state.taxPercent.toString())

                CustomText(
                    text = "Income fee precent : $incomeTax %",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 5.dp, 0.dp, 0.dp),
                    fontSize = 16,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold
                )

                CustomText(
                    text = "Sales fee precent : $salesTax %",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 5.dp, 0.dp, 0.dp),
                    fontSize = 16,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold
                )

                CustomSwitch(isChecked = isChecked)

                CustomButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(85.dp)
                        .padding(30.dp, 15.dp, 30.dp, 15.dp)
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    if (isChecked.value)
                        mainInput.value = saleTaxCalculator(mainInput.value, state.taxPercent)
                    else
                        mainInput.value = incomeTaxCalculator(mainInput.value, state.incomeTax)
                }
            }
        }
    }
}

@Composable
fun CustomSwitch(isChecked: MutableState<Boolean>) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(10.dp)
    ) {
        Text(text = "Income tax",
            modifier = Modifier.padding(0.dp,0.dp,15.dp,0.dp))
        Switch(checked = isChecked.value, onCheckedChange = {
            isChecked.value = !isChecked.value
        })
        Text(text = "Buy product tax",
        modifier = Modifier.padding(15.dp,0.dp,0.dp,0.dp))
    }
}


@Composable
fun BottomView(image: Int = 0) {
    Box(
        modifier = Modifier
            .padding(0.dp, 10.dp, 0.dp, 0.dp)
    ) {
        Surface(
            modifier = Modifier
                .width(315.dp)
                .height(200.dp)
                .padding(25.dp, 10.dp)
                .border(width = 1.dp, color = Color.LightGray)
                .clip(RoundedCornerShape(10.dp))
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = image),
                    contentDescription = "State Map",
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UnitedStatesTaxAppJetpackComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            HomeScreen()
        }
    }
}