package com.crype.cargame

import android.app.Application
import com.crype.cargame.di.dataStoreModule
import com.crype.cargame.di.repositoryModule
import com.crype.cargame.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(dataStoreModule, repositoryModule, viewModelModule)
        }
    }
}