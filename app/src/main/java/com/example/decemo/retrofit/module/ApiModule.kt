package com.example.decemo.retrofit.module

import com.example.decemo.retrofit.Connect.provideForecastApi
import com.example.decemo.retrofit.Connect.provideOkHttpClient
import com.example.decemo.retrofit.Connect.provideRetrofit
import org.koin.dsl.module

val networkModule = module {
//    factory { AuthInterceptor() }
    factory { provideOkHttpClient() }
    factory { provideForecastApi(get()) }
    single { provideRetrofit(get()) }
}