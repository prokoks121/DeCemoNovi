package com.example.decemo.coordinator

import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.decemo.MainActivity
import com.example.decemo.R
import com.example.decemo.coordinator.navigations.SearchCoordinator
import com.example.decemo.ui.view.BarFragment
import com.example.decemo.ui.view.HomeFragment
import com.example.decemo.ui.view.HomeFragmentDirections
import com.example.decemo.ui.view.SearchFragment
import com.example.decemo.ui.viewmodel.BaseViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AndroidRouter(private val activity: MainActivity) : Router() {

    private val navigation by lazy { (activity.supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment).navController }
    private val bottomNavigation: BottomNavigationView

    init {
        bottomNavigation = activity.findViewById<BottomNavigationView>(R.id.nav_view)
        a()
    }

    override fun navigateTo(destination: Destination, onFinish: (baseViewModel: BaseViewModel) -> Unit) {
        when (destination) {
            Destination.SPLASH -> {
//                activity.fragmentCreated<SplashFragment> {
//                    onFinish(it.getViewModel())
//                }
            }

            Destination.MAPS -> {
                activity.fragmentCreated<HomeFragment> {
                    onFinish(it.getViewModel())
                }
            }

            Destination.BAR -> {
                navigation.navigate(HomeFragmentDirections.actionHome2ToBarFragment())
                activity.fragmentCreated<BarFragment> {
                    onFinish(it.getViewModel())
                }
            }

            Destination.SEARCH -> {
//                navigation.navigate(HomeFragmentDirections.actionHome2ToSearch())
                activity.fragmentCreated<SearchFragment> {
                    onFinish(it.getViewModel())
                }
            }
        }
    }

    fun a() {
        bottomNavigation.setupWithNavController(navigation)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.title){
                "Pretraga" ->{
                    SearchCoordinator(this).navigate()
                }
            }
            true
        }
        navigation.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
//                R.id.storyFragment -> hideBottomNav(bottomNavigation)

                else -> showBottomNav(bottomNavigation)
            }
        }
    }


    private fun showBottomNav(bottomNav: BottomNavigationView) {
        bottomNav.visibility = View.VISIBLE

    }

    private fun hideBottomNav(bottomNav: BottomNavigationView) {
        bottomNav.visibility = View.GONE

    }
}