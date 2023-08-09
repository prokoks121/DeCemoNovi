package com.example.decemo.ui.viewmodel

import com.example.decemo.model.BarEvent
import com.example.decemo.repository.Repository

class StoryViewModel(repository: Repository) : BaseViewModel(repository) {

    lateinit var barEvents: List<BarEvent>
    var startPosition: Int = 0
}