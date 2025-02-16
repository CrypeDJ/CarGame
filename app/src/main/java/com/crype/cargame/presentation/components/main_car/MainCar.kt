package com.crype.cargame.presentation.components.main_car

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.crype.cargame.R
import com.crype.cargame.presentation.viewmodel.MainCarViewModel

@Composable
fun MainCar(
    isRightPressed: Boolean,
    isLeftPressed: Boolean,
    speed: Int,
    paddingValues: PaddingValues,
    viewModel: MainCarViewModel = viewModel()
) {
    var columnWidth by remember {
        mutableStateOf(0)
    }
    var carWidth by remember {
        mutableStateOf(0)
    }
    if (isRightPressed || isLeftPressed) {
        viewModel.startMoving(
            isRightPress = isRightPressed,
            isLeftPress = isLeftPressed,
            minX = 0,
            maxX = columnWidth - carWidth,
            speed = speed
        )
    }
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .height(100.dp)
            .onSizeChanged { size ->
                columnWidth = size.width
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.car),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .offset { IntOffset(viewModel.position.value, 0) }
                .onSizeChanged { size ->
                    carWidth = size.width
                }
                .height(100.dp),
        )
    }
}