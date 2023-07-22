package com.example.decemo.ui.viewmodel

import com.example.decemo.repository.Repository
import com.example.decemo.retrofit.dto.BarEvent

class StoryViewModel(repository: Repository) : BaseViewModel(repository) {

    lateinit var barEvents: List<BarEvent>
    var startPosition: Int = 0
}