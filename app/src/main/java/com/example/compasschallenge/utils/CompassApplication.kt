package com.example.compasschallenge.utils

import android.app.Application
import com.example.compasschallenge.di.DiWrapper

class CompassApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        DiWrapper.start(this)
    }
}