package com.crype.cargame.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlin.random.Random
import kotlin.random.nextInt

class PoliceCarViewModel : ViewModel() {

    fun delayOrDuration(
        minValue: Int,
        maxValue: Int
    ): Int {
        return Random.nextInt(minValue..maxValue)
    }
}