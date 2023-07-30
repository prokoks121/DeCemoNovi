package com.example.decemo.ui.viewmodel.module

import com.example.decemo.ui.viewmodel.ReservationViewModel
import org.koin.dsl.module

val reservationModule = module {
    factory { ReservationViewModel(get()) }
}