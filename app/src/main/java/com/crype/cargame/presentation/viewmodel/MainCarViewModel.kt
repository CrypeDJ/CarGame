package com.crype.cargame.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainCarViewModel : ViewModel() {

    private val _position = mutableStateOf(0)
    val position: State<Int> = _position
    private var job: Job? = null

    fun startMoving(
        isRightPress: Boolean,
        isLeftPress: Boolean,
        maxX: Int,
        minX: Int,
        speed: Int
    ) {
        if (!(isRightPress xor isLeftPress)) return
        job?.cancel()
        job = viewModelScope.launch {
            while (isActive) {
                _position.value = (_position.value + if (isRightPress) speed else -speed)
                    .coerceIn(minX, maxX)
                delay(16L)
            }
        }
    }
}