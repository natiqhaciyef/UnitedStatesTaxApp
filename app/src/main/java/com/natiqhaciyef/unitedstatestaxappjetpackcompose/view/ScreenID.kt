package com.natiqhaciyef.unitedstatestaxappjetpackcompose.view

enum class ScreenID {
    HomeScreen,
    RoomTrackingScreen,
    CoursesScreen,
    UserProfileScreen;

    fun routeBetweenScreens(route: String?) = when(route){
        HomeScreen.name -> HomeScreen
        RoomTrackingScreen.name -> RoomTrackingScreen
        CoursesScreen.name -> CoursesScreen
        UserProfileScreen.name -> UserProfileScreen
        null -> HomeScreen
        else -> throw IllegalArgumentException("Route $route is not required")
    }
}