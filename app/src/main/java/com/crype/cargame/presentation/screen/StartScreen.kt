package com.crype.cargame.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.crype.cargame.R
import com.crype.cargame.presentation.components.StartButton
import com.crype.cargame.presentation.navigation.Screens
import com.crype.cargame.presentation.ui.theme.BoxBackColor
import com.crype.cargame.presentation.viewmodel.GameViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun StartScreen(
    navController: NavController,
    viewModel: GameViewModel = koinViewModel()
) {
    val score by viewModel.highScore.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.main_background),
            contentDescription = "background",
            contentScale = ContentScale.FillHeight
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(1f)) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.game_name),
                    contentDescription = "game title"
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .background(color = BoxBackColor)
                ) {
                    Text(
                        text = score.toString(),
                        color = Color.White,
                        fontSize = 50.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
                        letterSpacing = 3.sp
                    )
                }
                Spacer(modifier = Modifier.padding(7.dp))
                Box(
                    modifier = Modifier
                        .background(color = BoxBackColor)
                ) {
                    Text(
                        text = stringResource(id = R.string.best_score),
                        color = Color.White,
                        fontSize = 30.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
                    )
                }
            }
            Box(modifier = Modifier.weight(1f)) {
                StartButton {
                    navController.navigate(route = Screens.GameScreen.route)
                }
            }
        }
    }
}