package com.crype.cargame.di

import com.crype.cargame.data.repository.ScoreRepositoryImpl
import com.crype.cargame.domain.repository.ScoreRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ScoreRepository> { ScoreRepositoryImpl(get()) }
}