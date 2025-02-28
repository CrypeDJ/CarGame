package com.crype.cargame.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.crype.cargame.domain.repository.ScoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ScoreRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : ScoreRepository {

    companion object {
        private val SCORE_KEY = intPreferencesKey("score")
    }

    override suspend fun saveScore(score: Int) {
        dataStore.edit { preferences ->
            preferences[SCORE_KEY] = score
        }
    }

    override fun getScore(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[SCORE_KEY] ?: 0
        }
    }
}