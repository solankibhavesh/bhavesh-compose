package com.bhavesh.compose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bhavesh.compose.presentation.screens.profilescreen.ProfileScreen
import com.bhavesh.compose.presentation.screens.registration.RegistrationScreen
import com.bhavesh.compose.presentation.state.BackButtonHandler
import com.bhavesh.compose.presentation.state.Screen
import com.bhavesh.compose.presentation.ui.theme.BhaveshComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BhaveshComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CreateProfile.route
                    ) {
                        composable(route = Screen.CreateProfile.route) {
                            RegistrationScreen(navController = navController)
                        }

                        composable(route = Screen.Profile.route) {
                            ProfileScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
