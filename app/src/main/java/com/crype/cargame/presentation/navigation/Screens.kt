package com.crype.cargame.presentation.navigation

sealed class Screens(val route: String) {
    object StartScreen : Screens(route = "start")
    object GameScreen : Screens(route = "game")
    object GameOverScreen : Screens(route = "game_over")
}