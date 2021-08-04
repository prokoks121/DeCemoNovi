package com.example.decemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.decemo.repository.Repository
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.nav_view)
        val navController: NavController = findNavController(R.id.navHost)
        bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.storyFragment -> hideBottomNav(bottomNavigation)
                else -> showBottomNav(bottomNavigation)
            }
        }
        Repository.getLokali()
        Repository.getDogadjaje()
    }

    private fun showBottomNav(bottomNav:BottomNavigationView) {
        bottomNav.visibility = View.VISIBLE

    }

    private fun hideBottomNav(bottomNav:BottomNavigationView) {
        bottomNav.visibility = View.GONE

    }
}