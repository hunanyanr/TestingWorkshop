package com.m2f.testingworkshop

import android.app.Application
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    environment,
                    businessModule,
                    posts
                )
            )
        }
    }
}