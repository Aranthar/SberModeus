package ru.sbermodeus

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SberModeusApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}