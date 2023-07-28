package com.example.decemo.retrofit.module

import android.app.Application
import com.example.decemo.retrofit.Connect.provideForecastApi
import com.example.decemo.retrofit.Connect.provideOkHttpClient
import com.example.decemo.retrofit.Connect.provideRetrofit
import com.example.decemo.token.TokenStorage
import org.koin.core.module.Module
import org.koin.dsl.module

fun networkModule(context: Application): Module = module {
//    factory { AuthInterceptor() }
    single<TokenStorage> {
        TokenStorage(context)
    }
    factory { provideOkHttpClient() }
    factory { provideForecastApi(get()) }
    single { provideRetrofit(get()) }
}