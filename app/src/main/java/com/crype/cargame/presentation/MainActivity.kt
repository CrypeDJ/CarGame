package com.crype.cargame.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.crype.cargame.presentation.navigation.NavGraph
import com.crype.cargame.presentation.navigation.Screens
import com.crype.cargame.presentation.ui.theme.CarGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarGameTheme {
                val navController = rememberNavController()
                NavGraph(
                    navController = navController,
                    startDestination = Screens.StartScreen.route
                )
            }
        }
    }
}

