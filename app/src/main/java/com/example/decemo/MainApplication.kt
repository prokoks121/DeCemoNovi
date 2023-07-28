package com.example.decemo

import android.app.Application
import com.example.decemo.repository.module.repositoryModule
import com.example.decemo.retrofit.module.networkModule
import com.example.decemo.ui.viewmodel.module.barModule
import com.example.decemo.ui.viewmodel.module.barSearchModule
import com.example.decemo.ui.viewmodel.module.homeModule
import com.example.decemo.ui.viewmodel.module.loginModule
import com.example.decemo.ui.viewmodel.module.registrationModule
import com.example.decemo.ui.viewmodel.module.searchModule
import com.example.decemo.ui.viewmodel.module.storyModule
import com.example.decemo.ui.viewmodel.module.userModule
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
                    networkModule(this@MainApplication),
                    repositoryModule,
                    homeModule,
                    barModule,
                    searchModule,
                    barSearchModule,
                    storyModule,
                    userModule,
                    loginModule,
                    registrationModule
                )
            )
        }
    }
}