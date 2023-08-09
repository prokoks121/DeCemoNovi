package com.example.decemo.coordinator.navigations

import com.example.decemo.coordinator.BaseCoordinator
import com.example.decemo.coordinator.Destination
import com.example.decemo.coordinator.Router
import com.example.decemo.model.BarEvent
import com.example.decemo.ui.viewmodel.StoryViewModel

class StoryCoordinator(router: Router) : BaseCoordinator(router) {
    override fun navigate(data: Any?) {
        router.navigateTo(Destination.STORY) {
            (it as StoryViewModel).apply {
                val pair = data as Pair<List<BarEvent>, Int>
                barEvents = pair.first
                startPosition = pair.second
            }
        }
    }
}