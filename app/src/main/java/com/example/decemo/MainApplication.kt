package com.example.decemo

import android.app.Application
import com.example.decemo.repository.module.repositoryModule
import com.example.decemo.retrofit.module.networkModule
import com.example.decemo.ui.viewmodel.module.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        koinInit()
    }

    private fun koinInit() {
        GlobalContext.startKoin {
            // Reference Android context
            androidContext(this@MainApplication)
            // Load modules
            modules(
                listOf(
                    repositoryModule,
                    networkModule,
                    homeModule
                )
            )
        }
    }
}