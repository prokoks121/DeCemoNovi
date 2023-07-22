package com.example.decemo.ui.viewmodel.module

import com.example.decemo.ui.viewmodel.BarSearchViewModel
import org.koin.dsl.module

val barSearchModule = module {
    factory { BarSearchViewModel(get()) }
}