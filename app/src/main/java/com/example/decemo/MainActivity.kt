package com.example.decemo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.example.decemo.coordinator.AndroidRouter
import com.example.decemo.coordinator.navigations.MapCoordinator
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var mainCoordinator: MapCoordinator
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.nav_view)
        val router = AndroidRouter(this)
        mainCoordinator = MapCoordinator(router)
        mainCoordinator.navigate()
//        navController = router.navigation
//        bottomNavigation.setupWithNavController(navController)

//        navController.addOnDestinationChangedListener { _, destination, _ ->
////            when (destination.id) {
////                R.id.storyFragment -> hideBottomNav(bottomNavigation)
////                else -> showBottomNav(bottomNavigation)
////            }
//        }
    }

    private fun showBottomNav(bottomNav: BottomNavigationView) {
        bottomNav.visibility = View.VISIBLE

    }

    private fun hideBottomNav(bottomNav: BottomNavigationView) {
        bottomNav.visibility = View.GONE

    }
}