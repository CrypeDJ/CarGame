package com.crype.cargame.presentation.components.main_car

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ControlButton(
    interactionSource: MutableInteractionSource,
    imageVector: ImageVector
) {
    IconButton(
        onClick = {},
        colors = IconButtonDefaults.iconButtonColors().copy(
            contentColor = Color.Black,
            containerColor = Color.Transparent
        ),
        interactionSource = interactionSource,
        modifier = Modifier.size(100.dp)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            modifier = Modifier.size(100.dp)
        )
    }
}