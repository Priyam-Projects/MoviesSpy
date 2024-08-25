package com.example.moviesspy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.moviesspy.presentation.AppNavHost
import com.example.moviesspy.presentation.screen.Screen
import com.example.moviesspy.presentation.screen.detailspage.DetailsPageViewModel
import com.example.moviesspy.ui.theme.MoviesSpyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesSpyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    val detailsPageViewModel = DetailsPageViewModel()
                    AppNavHost(
                        navController = navController,
                        startDestination = Screen.HomePageScreen.route,
                        detailsPageViewModel = detailsPageViewModel,
                    )
                }
            }
        }
    }
}
