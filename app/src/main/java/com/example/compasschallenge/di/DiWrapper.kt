package com.example.compasschallenge.di

import android.app.Application
import com.example.compasschallenge.flow.EveryTenCharactersUseCase
import com.example.compasschallenge.flow.WordCounterUseCase
import com.example.compasschallenge.viewModels.ResultsViewModel
import com.example.compasschallenge.viewModels.TextRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

object DiWrapper {

    fun start(application: Application) {
        startKoin {
            androidContext(application)
            androidLogger(Level.ERROR) //TODO: workaround for crash in current kotlin version (1.6 or lower)
            modules(
                listOf(
                    moduleViewModels(),
                    moduleRepositories(),
                    useCaseModules()
                )
            )
        }
    }

    private fun moduleViewModels() = module {
        factory { ResultsViewModel(get(), get()) }
    }

    private fun moduleRepositories() = module {
        single { TextRepository() }

    }
    private fun useCaseModules() = module {
        factory { WordCounterUseCase(get()) }
        factory { EveryTenCharactersUseCase(get()) }
    }

}