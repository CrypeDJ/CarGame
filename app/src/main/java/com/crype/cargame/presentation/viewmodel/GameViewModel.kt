package com.crype.cargame.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crype.cargame.domain.models.CarsModel
import com.crype.cargame.domain.repository.ScoreRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel(
    private val scoreRepository: ScoreRepository
) : ViewModel() {
    private val _position = mutableStateOf(0)
    val position: State<Int> = _position

    private val _timer = MutableStateFlow("03:00")
    val timer: StateFlow<String> = _timer
    private var timeLeft = 180

    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score

    private val _highScore = MutableStateFlow(0)
    val highScore: StateFlow<Int> = _highScore

    private val _isTimeOut = mutableStateOf(false)
    val isTimeOut: State<Boolean> = _isTimeOut

    private var job: Job? = null

    init {
        startCollisionCheck()
        startTimer()
        loadHighScore()
    }

    private val moveJob = MutableStateFlow<Job?>(null)

    fun startMoving(
        isRightPress: Boolean,
        isLeftPress: Boolean,
        maxX: Int,
        minX: Int,
        speed: Int
    ) {
        moveJob.value?.cancel()
        if (!(isRightPress xor isLeftPress)) return

        moveJob.value = viewModelScope.launch {
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
                newCars[index] = car.copy(
                    offsetY = car.offsetY,
                )
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

                val hasCollision = policeCars.any { it.rectCar.overlaps(mainCar.rectCar) }
                if (hasCollision) _collisionState.value = true

                policeCars.forEach { policeCar ->
                    if (policeCar.offsetY > mainCar.offsetY + mainCar.carSize.height &&
                        !policeCar.isChecked
                    ) {
                        _score.value += 1
                        policeCar.isChecked = true
                    }
                }
            }
        }
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (timeLeft > 0 && isActive) {
                delay(1000L)
                timeLeft--
                val minutes = timeLeft / 60
                val seconds = timeLeft % 60
                _timer.value = String.format("%02d:%02d", minutes, seconds)
            }
            _isTimeOut.value = true
        }
    }

    private fun loadHighScore() {
        viewModelScope.launch {
            scoreRepository.getScore()
                .collect { _highScore.value = it }
        }
    }

    fun saveHighScore() {
        viewModelScope.launch {
            if (_score.value > _highScore.value)
                scoreRepository.saveScore(_score.value)
        }
    }
}