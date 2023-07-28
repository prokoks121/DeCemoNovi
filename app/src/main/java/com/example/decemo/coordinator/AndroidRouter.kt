package com.example.decemo.coordinator

import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.decemo.MainActivity
import com.example.decemo.R
import com.example.decemo.coordinator.navigations.LoginCoordinator
import com.example.decemo.coordinator.navigations.MapCoordinator
import com.example.decemo.coordinator.navigations.SearchCoordinator
import com.example.decemo.coordinator.navigations.UserCoordinator
import com.example.decemo.token.getString
import com.example.decemo.ui.view.BarFragment
import com.example.decemo.ui.view.BarSearchFragment
import com.example.decemo.ui.view.HomeFragment
import com.example.decemo.ui.view.LoginFragment
import com.example.decemo.ui.view.SearchFragment
import com.example.decemo.ui.view.SearchFragmentDirections
import com.example.decemo.ui.view.StoryFragment
import com.example.decemo.ui.view.UserFragment
import com.example.decemo.ui.viewmodel.BaseViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class AndroidRouter(private val activity: MainActivity) : Router() {

    private val navigation by lazy { (activity.supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment).navController }
    private val bottomNavigation: BottomNavigationView = activity.findViewById(R.id.nav_view)

    init {
        setBottomNavigationMenu()
    }

    override fun navigateTo(destination: Destination, onFinish: (baseViewModel: BaseViewModel) -> Unit) {
        when (destination) {
            Destination.SPLASH -> {
//                activity.fragmentCreated<SplashFragment> {
//                    onFinish(it.getViewModel())
//                }
            }

            Destination.MAPS -> {
                navigation.navigate(R.id.action_global_home2)
                activity.fragmentCreated<HomeFragment> {
                    onFinish(it.getViewModel())
                }
            }

            Destination.BAR -> {
                navigation.navigate(R.id.action_global_barFragment)
                activity.fragmentCreated<BarFragment> {
                    onFinish(it.getViewModel())
                }
            }

            Destination.SEARCH -> {
                navigation.navigate(R.id.action_global_search)
                activity.fragmentCreated<SearchFragment> {
                    onFinish(it.getViewModel())
                }
            }

            Destination.BAR_SEARCH -> {
                navigation.navigate(SearchFragmentDirections.actionSearchToBarSearchFragment())
                activity.fragmentCreated<BarSearchFragment> {
                    onFinish(it.getViewModel())
                }
            }

            Destination.STORY -> {
                navigation.navigate(R.id.action_global_story)
                activity.fragmentCreated<StoryFragment> {
                    onFinish(it.getViewModel())
                }
            }

            Destination.LOGIN -> {
                navigation.navigate(R.id.action_global_loginFragment)
                activity.fragmentCreated<LoginFragment> {
                    onFinish(it.getViewModel())
                }
            }

            Destination.USER -> {
                navigation.navigate(R.id.action_global_userFragment)
                activity.fragmentCreated<UserFragment> {
                    onFinish(it.getViewModel())
                }
            }
        }
    }

    private fun setBottomNavigationMenu() {
        bottomNavigation.setupWithNavController(navigation)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.search_menu_item -> {
                    SearchCoordinator(this).navigate()
                }

                R.id.home_menu_item -> {
                    MapCoordinator(this).navigate()
                }

                R.id.user_menu_item -> {
                    if (activity.application.getString("ACCESS_TOKEN").isNullOrEmpty()) {
                        LoginCoordinator(this).navigate()
                    } else {
                        UserCoordinator(this).navigate()
                    }
                }
            }
            false
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