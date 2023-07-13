package com.example.decemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.example.decemo.coordinator.AndroidRouter
import com.example.decemo.coordinator.navigations.MapCoordinator
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var mainCoordinator: MapCoordinator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val router = AndroidRouter(this)
        mainCoordinator = MapCoordinator(router)
        mainCoordinator.navigate()
    }
}