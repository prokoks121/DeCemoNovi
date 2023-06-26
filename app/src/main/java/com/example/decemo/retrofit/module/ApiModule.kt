package com.example.decemo.retrofit.module

import com.example.decemo.retrofit.ApiConnection
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
//    factory { AuthInterceptor() }
    factory { provideOkHttpClient() }
    factory { provideForecastApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    val url = "http://10.0.2.2:8080/"
    return Retrofit.Builder().baseUrl(url).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(): OkHttpClient {
//    https://medium.com/@harmittaa/retrofit-2-6-0-with-koin-and-coroutines-4ff45a4792fc
//    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
    return OkHttpClient().newBuilder().build()

}

fun provideForecastApi(retrofit: Retrofit): ApiConnection = retrofit.create(ApiConnection::class.java)
