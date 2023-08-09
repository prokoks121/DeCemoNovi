package com.example.decemo.ui.viewmodel.module

import com.example.decemo.ui.viewmodel.ReservationUpdateViewModel
import org.koin.dsl.module


val reservationUpdateModule = module {
    factory { ReservationUpdateViewModel(get()) }
}