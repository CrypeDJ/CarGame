package com.crype.cargame.presentation.screen

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.crype.cargame.presentation.components.CarControl
import com.crype.cargame.presentation.components.Road

@Composable
fun GameScreen() {
    val rightInteractionSource = remember { MutableInteractionSource() }
    val rightPressed by rightInteractionSource.collectIsPressedAsState()
    val leftInteractionSource = remember { MutableInteractionSource() }
    val leftPressed by rightInteractionSource.collectIsPressedAsState()
    Road()

    CarControl(
        leftInteractionSource = leftInteractionSource,
        rightInteractionSource = rightInteractionSource
    )
}

@Preview
@Composable
fun GamePreview() {
    GameScreen()
}