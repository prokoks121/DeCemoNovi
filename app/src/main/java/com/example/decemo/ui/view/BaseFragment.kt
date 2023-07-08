package com.example.decemo.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.decemo.ui.viewmodel.BaseViewModel

abstract class BaseFragment(private val layoutResId: Int) : Fragment(layoutResId) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(layoutResId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel().onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        getViewModel().onViewResumed()
    }

    override fun onPause() {
        super.onPause()
        getViewModel().onViewPaused()
    }

    abstract fun getViewModel(): BaseViewModel
}