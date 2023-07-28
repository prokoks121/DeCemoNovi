package com.example.decemo.ui.viewmodel.module

import com.example.decemo.ui.viewmodel.LoginViewModel
import org.koin.dsl.module

val loginModule = module {
    factory { LoginViewModel(get()) }
}