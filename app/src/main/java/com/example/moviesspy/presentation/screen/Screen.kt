package com.example.moviesspy.presentation.screen

sealed class Screen(val route: String) {

    object HomePageScreen: Screen(ScreenTypes.HOME.name)

    object DetailPageScreen: Screen(ScreenTypes.DETAIL.name)
}

enum class ScreenTypes {
    HOME,
    DETAIL,
}