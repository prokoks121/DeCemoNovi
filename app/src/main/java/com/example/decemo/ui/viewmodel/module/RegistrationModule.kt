package com.example.decemo.ui.viewmodel.module

import com.example.decemo.ui.viewmodel.RegistrationViewModel
import org.koin.dsl.module

val registrationModule = module {
    factory { RegistrationViewModel(get()) }
}