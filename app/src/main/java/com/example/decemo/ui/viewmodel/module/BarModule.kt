package com.example.decemo.ui.viewmodel.module

import com.example.decemo.ui.viewmodel.BarViewModel
import org.koin.dsl.module

val barModule = module {
    factory { BarViewModel(get()) }
}