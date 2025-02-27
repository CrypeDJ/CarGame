package com.crype.cargame.domain.repository

import kotlinx.coroutines.flow.Flow

interface ScoreRepository {
    suspend fun saveScore(score: Int)
    fun getScore(): Flow<Int>
}