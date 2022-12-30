package com.natiqhaciyef.unitedstatestaxappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.natiqhaciyef.unitedstatestaxappjetpackcompose.*
import com.natiqhaciyef.unitedstatestaxappjetpackcompose.data.items.StatesList
import com.natiqhaciyef.unitedstatestaxappjetpackcompose.data.items.StatesNameList
import com.natiqhaciyef.unitedstatestaxappjetpackcompose.data.model.UnitedState
import com.natiqhaciyef.unitedstatestaxappjetpackcompose.ui.theme.UnitedStatesTaxAppJetpackComposeTheme
import com.natiqhaciyef.unitedstatestaxappjetpackcompose.util.*
import com.natiqhaciyef.unitedstatestaxappjetpackcompose.view.DetailsScreen
import com.natiqhaciyef.unitedstatestaxappjetpackcompose.view.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitedStatesTaxAppJetpackComposeTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "home_screen"
                ) {
                    composable("home_screen") {
                        HomeScreen(navController)
                    }

                    composable("details_screen"){
                        DetailsScreen(navController, StatesList.statesList)
                    }
                }
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    HomeScreen(navController = navController)
//                    MainView(StatesList.statesList)
//                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UnitedStatesTaxAppJetpackComposeTheme(false) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "home_screen"
        ) {
            composable("home_screen") {
                HomeScreen(navController)
            }

            composable("details_screen"){
                DetailsScreen(navController,StatesList.statesList)
            }
        }
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colors.background,
//        ) {
//            R.drawable.dollar_icon
//            HomeScreen(navController = navController)
//            MainView(statesList = StatesList.statesList)
//        }
    }
}