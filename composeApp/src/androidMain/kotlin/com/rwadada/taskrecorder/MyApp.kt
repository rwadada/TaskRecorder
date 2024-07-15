package com.rwadada.taskrecorder

import android.app.Application
import di.appModule
import org.koin.core.context.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}