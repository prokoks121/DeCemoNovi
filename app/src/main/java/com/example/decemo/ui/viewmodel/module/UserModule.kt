package com.example.decemo.ui.viewmodel.module

import com.example.decemo.ui.viewmodel.UserViewModel
import org.koin.dsl.module

val userModule = module {
    factory { UserViewModel(get()) }
}