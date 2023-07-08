package com.example.decemo.coordinator

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import com.example.decemo.ui.view.BaseFragment

inline fun <reified T : BaseFragment> FragmentActivity.fragmentCreated(crossinline finished: (fragment: T) -> Unit) {
    var fragment: T
    this.getChildFragmentManager.registerFragmentLifecycleCallbacks(
        object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
                super.onFragmentResumed(fm, f)
                if (f is T) {
                    this@fragmentCreated.getChildFragmentManager.unregisterFragmentLifecycleCallbacks(this)
                    fragment = f
                    finished(fragment)
                }
            }

            override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
                super.onFragmentCreated(fm, f, savedInstanceState)
                if (f is T) {
                    this@fragmentCreated.getChildFragmentManager.unregisterFragmentLifecycleCallbacks(this)
                    fragment = f
                    finished(fragment)
                }
            }
        },
        false
    )
}

val FragmentActivity.getChildFragmentManager: FragmentManager
    get() = supportFragmentManager.fragments.find { it is NavHostFragment }!!.childFragmentManager