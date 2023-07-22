package com.example.decemo.ui.viewmodel.module

import com.example.decemo.ui.viewmodel.StoryViewModel
import org.koin.dsl.module

val storyModule = module {
    factory { StoryViewModel(get()) }
}