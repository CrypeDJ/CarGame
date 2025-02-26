package com.crype.cargame.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.crype.cargame.presentation.screen.GameOverScreen
import com.crype.cargame.presentation.screen.GameScreen
import com.crype.cargame.presentation.screen.StartScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screens.StartScreen.route) {
            StartScreen(navController)
        }
        composable(route = Screens.GameScreen.route) {
            GameScreen(navController)
        }
        composable(route = Screens.GameOverScreen.route) {
            GameOverScreen(navController)
        }
    }
}