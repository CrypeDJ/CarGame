package com.crype.cargame.domain.models

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size

data class CarsModel(
    var id: Int = 0,
    var offsetY: Float = 0f,
    var offsetX: Float = 0f,
    var carSize: Size = Size.Zero,
) {
    val rectCar: Rect
        get() = Rect(
            offsetX,
            offsetY,
            offsetX + carSize.width,
            offsetY + carSize.height
        )
}
