package com.bhavesh.compose.presentation.state

sealed class Screen(val route: String) {
    object CreateProfile : Screen(route = "create_profile")
    object Profile : Screen(route = "profile")
}