package com.example.notes.di

import android.content.Context
import androidx.startup.Initializer
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.dsl.koinApplication

internal class KoinInit : Initializer<Koin> {
    override fun create(context: Context): Koin {
        return koinApplication {
            androidContext(context)
            modules( listOf( databaseModules, repositoryModule,viewModel, useCaseModules ))
        }.koin
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}