package com.example.decemo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.decemo.model.Dogadjaj
import com.example.decemo.ui.view.StoryFragment

class StoryViewModel:ViewModel() {

    val data by lazy {
        MutableLiveData<StoryFragment.Data>()
    }

}