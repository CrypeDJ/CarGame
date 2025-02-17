package com.crype.cargame.presentation.screen

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.crype.cargame.presentation.components.main_car.CarControl
import com.crype.cargame.presentation.components.main_car.MainCar
import com.crype.cargame.presentation.components.police_car.PoliceCar
import com.crype.cargame.presentation.components.road.Road
import com.crype.cargame.presentation.viewmodel.PoliceCarViewModel

@Composable
fun GameScreen(
    policeCarViewModel: PoliceCarViewModel = viewModel()
) {
    val rightInteractionSource = remember { MutableInteractionSource() }
    val rightPressed by rightInteractionSource.collectIsPressedAsState()
    val leftInteractionSource = remember { MutableInteractionSource() }
    val leftPressed by leftInteractionSource.collectIsPressedAsState()
    Road()
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 55.dp)
            .fillMaxWidth()
    ) {
        (1..3).forEach { _ ->
            PoliceCar(
                durationMillis = policeCarViewModel.delayOrDuration(
                    minValue = 7000,
                    maxValue = 15000
                ),
                delay = policeCarViewModel.delayOrDuration(
                    minValue = 0,
                    maxValue = 5000,
                )
            )
        }
    }
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
    ) {
        MainCar(
            isRightPressed = rightPressed,
            isLeftPressed = leftPressed,
            paddingValues = PaddingValues(horizontal = 30.dp),
            speed = 10
        )
        CarControl(
            leftInteractionSource = leftInteractionSource,
            rightInteractionSource = rightInteractionSource
        )
        Spacer(modifier = Modifier.padding(20.dp))
    }
}

@Preview
@Composable
fun GamePreview() {
    GameScreen()
}