package com.crype.cargame.presentation.components.police_car

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.crype.cargame.R
import kotlin.math.roundToInt

@Composable
fun PoliceCar(
    durationMillis: Int,
    delay: Int,
) {
    var roadHeight by remember {
        mutableStateOf(0)
    }
    var carHeight by remember {
        mutableStateOf(0)
    }
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val offsetY by infiniteTransition.animateFloat(
        initialValue = -carHeight.toFloat(),
        targetValue = (roadHeight + carHeight * 2).toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = durationMillis,
                delayMillis = delay,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart,
            initialStartOffset = StartOffset(roadHeight / 2, StartOffsetType.Delay)
        ),
        label = ""
    )
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .onSizeChanged { size ->
                roadHeight = size.height
            },
    ) {
        Image(
            painter = painterResource(id = R.drawable.car_police),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(100.dp)
                .offset {
                    IntOffset(0, offsetY.roundToInt())
                }
                .onSizeChanged { size ->
                    carHeight = size.height
                }
        )
    }
}