package com.natiqhaciyef.unitedstatestaxappjetpackcompose.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.natiqhaciyef.unitedstatestaxappjetpackcompose.*
import com.natiqhaciyef.unitedstatestaxappjetpackcompose.data.items.StatesNameList
import com.natiqhaciyef.unitedstatestaxappjetpackcompose.data.model.UnitedState
import com.natiqhaciyef.unitedstatestaxappjetpackcompose.util.*


@Composable
fun DetailsScreen(navController: NavController, statesList: List<UnitedState>) {
    Surface(
        modifier = Modifier
            .padding(5.dp)
    ) {
        Column {
            CustomText(
                text = "About States",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                fontSize = 25,
                fontWeight = FontWeight.ExtraBold
            )
            LazyColumn {
                items(statesList) { item ->
                    LazyColumnItems(item)
                }
            }
        }
    }
}

//@Preview
@Composable
fun LazyColumnItems(state: UnitedState) {
    Card(
        modifier = Modifier
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = MaterialTheme.colors.onError)
                .height(60.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(75.dp)
                        .padding(5.dp, 0.dp, 0.dp, 0.dp),
                    painter = painterResource(id = state.image),
                    contentDescription = "State Image"
                )

                Column(
                    modifier = Modifier.weight(0.7f),
                    verticalArrangement = Arrangement.Center
                ) {
                    CustomText(
                        text = state.state,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 16,
                        fontWeight = FontWeight.Bold
                    )

                    CustomText(
                        text = "State code: ${state.stateCode}",
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 14,
                        fontWeight = FontWeight.Normal
                    )

                }
                Column(
                    modifier = Modifier
                        .weight(0.3f)
                        .padding(0.dp, 0.dp, 10.dp, 0.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    CustomText(
                        text = "Min wage",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 0.dp, 0.dp, 5.dp),
                        fontSize = 15,
                        fontWeight = FontWeight.Bold
                    )

                    val minWage = "%.2f".format(state.minWage)
                    CustomText(
                        text = "$minWage $",
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 15,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}