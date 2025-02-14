package com.crype.cargame.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.ceil
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size

@Composable
fun RoadLine(
    time: Int,
) {
    val stripeWidth = 30f
    val stripeHeight = 250f
    val stripeSpacing = 130f
    val totalHeight = stripeHeight + stripeSpacing

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = totalHeight,
        animationSpec = infiniteRepeatable(tween(time, easing = LinearEasing)),
        label = ""
    )

    Canvas(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        val stripeCount = ceil(size.height / totalHeight).toInt() + 1
        val centerX = size.width / 2
        val stripeXStart = centerX - stripeWidth / 2

        val topRectHeight = stripeHeight * (offsetY / stripeHeight) - stripeSpacing
        if (offsetY > stripeSpacing) {
            drawRect(
                color = Color.White,
                topLeft = androidx.compose.ui.geometry.Offset(stripeXStart, 0f),
                size = androidx.compose.ui.geometry.Size(stripeWidth, topRectHeight)
            )
        }
        for (i in 0 until stripeCount) {
            val y = (i * totalHeight + offsetY) % size.height

            drawRect(
                color = Color.White,
                topLeft = Offset(stripeXStart, y),
                size = Size(stripeWidth, stripeHeight)
            )
        }
    }
}
