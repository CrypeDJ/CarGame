package com.crype.cargame.presentation.components.main_car

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import com.crype.cargame.R
import com.crype.cargame.domain.models.CarsModel
import com.crype.cargame.presentation.viewmodel.GameViewModel
import kotlin.math.roundToInt

@Composable
fun MainCar(
    isRightPressed: Boolean,
    isLeftPressed: Boolean,
    speed: Int,
    paddingValues: PaddingValues,
    carState: (CarsModel) -> Unit,
    modifier: Modifier,
    viewModel: GameViewModel = viewModel()
) {
    var columnWidth by remember { mutableStateOf(0) }
    val mainCar = remember { mutableStateOf(CarsModel()) }

    LaunchedEffect(isRightPressed, isLeftPressed) {
        viewModel.startMoving(
            isRightPress = isRightPressed,
            isLeftPress = isLeftPressed,
            minX = 0,
            maxX = columnWidth - mainCar.value.carSize.width.roundToInt(),
            speed = speed
        )
    }

    LaunchedEffect(mainCar.value) {
        Log.d(viewModel.position.value.toString(),viewModel.position.value.toString())
        mainCar.value = mainCar.value.copy(offsetX = viewModel.position.value.toFloat())
        carState(mainCar.value)
    }

    Box(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .height(100.dp)
            .onSizeChanged { size -> columnWidth = size.width }
    ) {
        Image(
            painter = painterResource(id = R.drawable.car),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .offset { IntOffset(viewModel.position.value, 0) }
                .onSizeChanged { size -> mainCar.value.carSize = size.toSize() }
                .height(100.dp),
        )
    }
}
