package com.example.decemo.ui.viewmodel.module

import com.example.decemo.ui.viewmodel.SearchViewModel
import org.koin.dsl.module

val searchModule = module {
    factory { SearchViewModel(get()) }
}