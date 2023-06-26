package com.example.decemo.ui.viewmodel.module

import com.example.decemo.ui.viewmodel.HomeViewModel
import org.koin.dsl.module

val homeModule = module {
    factory { HomeViewModel(get()) }
}