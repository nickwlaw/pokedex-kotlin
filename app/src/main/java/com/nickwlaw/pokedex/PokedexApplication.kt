package com.nickwlaw.pokedex

import android.app.Application
import com.nickwlaw.pokedex.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokedexApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PokedexApplication)
            modules(appModule)
        }
    }
}