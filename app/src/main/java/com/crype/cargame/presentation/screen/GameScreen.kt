package com.crype.cargame.presentation.screen

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.crype.cargame.presentation.components.Score
import com.crype.cargame.presentation.components.Timer
import com.crype.cargame.presentation.components.main_car.CarControl
import com.crype.cargame.presentation.components.main_car.MainCar
import com.crype.cargame.presentation.components.police_car.PoliceCar
import com.crype.cargame.presentation.components.road.Road
import com.crype.cargame.presentation.navigation.Screens
import com.crype.cargame.presentation.viewmodel.GameViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun GameScreen(
    navController: NavController,
    viewModel: GameViewModel = koinViewModel()
) {
    val rightInteractionSource = remember { MutableInteractionSource() }
    val rightPressed by rightInteractionSource.collectIsPressedAsState()
    val leftInteractionSource = remember { MutableInteractionSource() }
    val leftPressed by leftInteractionSource.collectIsPressedAsState()

    var carHeight by remember { mutableStateOf(0f) }
    var roadWidth by remember { mutableStateOf(0f) }

    val collision by viewModel.collisionState.collectAsState()
    val isTimeOut by viewModel.isTimeOut

    LaunchedEffect(collision, isTimeOut) {
        if (collision || isTimeOut) {
            viewModel.saveHighScore()

            val route = if (collision) Screens.GameOverScreen.route else Screens.StartScreen.route
            navController.navigate(route) {
                popUpTo(navController.graph.startDestinationId)
            }
        }
    }

    Road()
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 55.dp)
            .fillMaxWidth()
            .onSizeChanged {
                roadWidth = it.width.toFloat()
            }
    ) {
        repeat(3) { i ->
            PoliceCar(
                id = i,
                durationMillis = viewModel.generateDuration(i),
                delay = viewModel.generateDelay(i),
                carState = { car ->
                    car.offsetX = when (i) {
                        0 -> 0f
                        1 -> (roadWidth / 2) - (car.carSize.width / 2)
                        2 -> roadWidth - car.carSize.width
                        else -> 0f
                    }
                    viewModel.updatePoliceCar(car)
                }
            )
        }
    }

    MainCar(
        isRightPressed = rightPressed,
        isLeftPressed = leftPressed,
        paddingValues = PaddingValues(start = 55.dp, end = 55.dp, top = 630.dp),
        speed = 10,
        carState = { carState ->
            carState.offsetY = carHeight - carState.carSize.height
            viewModel.updateMainCar(carState)
        },
        modifier = Modifier.onSizeChanged { carHeight = it.height.toFloat() }
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        CarControl(
            leftInteractionSource = leftInteractionSource,
            rightInteractionSource = rightInteractionSource
        )
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 50.dp, vertical = 30.dp)
            .fillMaxWidth()
    ) {
        Timer()
        Score()
    }
}


@Preview
@Composable
fun Preview() {
    GameScreen(navController = rememberNavController())
}
