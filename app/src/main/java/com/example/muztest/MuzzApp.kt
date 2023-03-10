package com.example.muztest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MuzzApp : Application() {
    override fun onCreate() {
        super.onCreate()
        println("Muzz app started")
    }
}