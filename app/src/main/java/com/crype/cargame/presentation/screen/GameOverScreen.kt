package com.crype.cargame.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.crype.cargame.R
import com.crype.cargame.presentation.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun GameOverScreen(
    navController: NavController
) {
    LaunchedEffect(navController) {
        delay(3000L)
        navController.navigate(route = Screens.StartScreen.route)
    }
    Box(
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.game_over),
            contentDescription = ""
        )
    }
}