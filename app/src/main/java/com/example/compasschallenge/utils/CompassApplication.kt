package com.example.compasschallenge.utils

import android.app.Application
import com.example.compasschallenge.di.DiWrapper

class CompassApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        /**
         * When the app start, we need start Koin to load the modules and begin to inject dependencies
         */
        DiWrapper.start(this)

    }

}