package com.crype.cargame.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crype.cargame.domain.models.CarsModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel : ViewModel() {
    private val _position = mutableStateOf(0)
    val position: State<Int> = _position

    private var job: Job? = null

    init {
        startCollisionCheck()
    }

    fun startMoving(
        isRightPress: Boolean,
        isLeftPress: Boolean,
        maxX: Int,
        minX: Int,
        speed: Int
    ) {
        job?.cancel()
        if (!(isRightPress xor isLeftPress)) return

        job = viewModelScope.launch {
            while (isActive) {
                _position.value = (_position.value + if (isRightPress) speed else -speed)
                    .coerceIn(minX, maxX)
                _mainCarState.value.offsetX = _position.value.toFloat()
                delay(16L)
            }
        }
    }

    fun delayOrDuration(minValue: Int, maxValue: Int): Int {
        return Random.nextInt(minValue, maxValue)
    }

    private val _policeCarsState = MutableStateFlow<List<CarsModel>>(emptyList())

    fun updatePoliceCar(car: CarsModel) {
        _policeCarsState.update { cars ->
            val newCars = cars.toMutableList()
            val index = newCars.indexOfFirst { it.id == car.id }
            if (index != -1) {
                newCars[index] = car.copy(offsetY = car.offsetY)
            } else {
                newCars.add(car)
            }
            newCars
        }
    }

    private val _mainCarState = MutableStateFlow(CarsModel())

    fun updateMainCar(car: CarsModel) {
        _mainCarState.value = car.copy(offsetX = _position.value.toFloat())
    }

    private val _collisionState = MutableStateFlow(false)
    val collisionState: StateFlow<Boolean> = _collisionState

    private fun startCollisionCheck() {
        viewModelScope.launch {
            while (isActive) {
                delay(16L)
                val mainCar = _mainCarState.value
                val policeCars = _policeCarsState.value

                val hasCollision = policeCars.any { policeCar ->
                    policeCar.rectCar.overlaps(mainCar.rectCar)
                }

                if (hasCollision && !_collisionState.value) {
                    _collisionState.value = true
                }
            }
        }
    }
}