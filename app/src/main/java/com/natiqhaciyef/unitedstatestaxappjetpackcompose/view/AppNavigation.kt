package com.natiqhaciyef.unitedstatestaxappjetpackcompose.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenID.HomeScreen.name){
        composable(ScreenID.HomeScreen.name){
//            HomeScreen()
        }

        composable(
            route = "${ScreenID.HomeScreen.name}/{id}/{name}",
            arguments = listOf(
                navArgument(name = "id"){
                    type = NavType.IntType
                },
                navArgument(name = "name"){
                    type = NavType.StringType
                }
            )
        ){
            val id = remember{
                it.arguments?.getInt("id")
            }

            val name = remember{
                it.arguments?.getString("name")
            }
        }
    }
}