package com.example.moviesspy.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviesspy.presentation.screen.Screen
import com.example.moviesspy.presentation.screen.detailspage.DetailsPageUi
import com.example.moviesspy.presentation.screen.detailspage.DetailsPageViewModel
import com.example.moviesspy.presentation.screen.homepage.HomePageUi

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
    detailsPageViewModel: DetailsPageViewModel,
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination,
    ) {
        composable(Screen.HomePageScreen.route) {
            // todo see Use saveInstanceBundle to get the previous state for initial state of home screen
            HomePageUi(
                navController = navController,
                detailsViewModel = detailsPageViewModel,
            )
        }
        composable(Screen.DetailPageScreen.route) {
            DetailsPageUi(
                navController = navController,
                viewModel = detailsPageViewModel,
            )
        }
    }
}

