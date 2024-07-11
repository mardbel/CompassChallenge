package com.example.compasschallenge.di

import android.app.Application
import com.example.compasschallenge.viewModels.ResultsViewModel
import com.example.compasschallenge.viewModels.TextRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

object DiWrapper {

    private const val CACHE_DIR = "http-cache"

    /**
     * Initialize Koin library and load your custom modules
     */
    fun start(application: Application) {
        startKoin {
            androidContext(application)
            androidLogger(Level.ERROR) //TODO: workaround for crash in current kotlin version (1.6 or lower)
            modules(
                listOf(
                    moduleViewModels(),
                    moduleRepositories()
                )
            )
        }
    }

    /**
     * whenever you have a new viewmodel, add it to the dependency injection
     * [get()] is a Koin extension to inject in your class another dependency also created by Koin
     */
    private fun moduleViewModels() = module {
        factory { ResultsViewModel(get()) }
    }

    private fun moduleRepositories() = module {
        factory { TextRepository() }

    }
}