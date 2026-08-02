package com.example.trackify

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Show/Hide toolbar based on destination
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.introFragment) {
                toolbar.visibility = View.GONE
            } else {
                toolbar.visibility = View.VISIBLE
            }
        }

        toolbar.setupWithNavController(navController, AppBarConfiguration(navController.graph))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return when (item.itemId) {
            R.id.menu_edit_details -> {
                navController.navigate(R.id.detailsFragment)
                true
            }
            R.id.menu_edit_progress -> {
                navController.navigate(R.id.inputFragment)
                true
            }
            R.id.menu_view_summary -> {
                navController.navigate(R.id.summaryFragment)
                true
            }
            R.id.menu_view_calendar -> {
                navController.navigate(R.id.calendarFragment)
                true
            }
            R.id.user_name_display -> {
                navController.navigate(R.id.profileFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}