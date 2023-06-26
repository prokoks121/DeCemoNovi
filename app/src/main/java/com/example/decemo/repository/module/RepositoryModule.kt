package com.example.decemo.repository.module

import com.example.decemo.repository.Repository
import org.koin.dsl.module


val repositoryModule = module {
    factory { Repository(get()) }
}