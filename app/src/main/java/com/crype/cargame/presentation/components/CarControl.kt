package com.crype.cargame.presentation.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CarControl(
    rightInteractionSource: MutableInteractionSource,
    leftInteractionSource: MutableInteractionSource
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        ControlButton(
            interactionSource = leftInteractionSource,
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft
        )
        ControlButton(
            interactionSource = rightInteractionSource,
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight
        )
    }
}