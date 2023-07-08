package com.example.decemo.coordinator

import androidx.navigation.fragment.NavHostFragment
import com.example.decemo.MainActivity
import com.example.decemo.R
import com.example.decemo.ui.view.BarFragment
import com.example.decemo.ui.view.HomeFragment
import com.example.decemo.ui.view.HomeFragmentDirections
import com.example.decemo.ui.viewmodel.BaseViewModel

class AndroidRouter(private val activity: MainActivity) : Router() {

    private val navigation by lazy { (activity.supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment).navController }

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
        }
    }
}