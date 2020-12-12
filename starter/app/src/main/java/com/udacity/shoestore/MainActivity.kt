package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActivityConfigurations()
        setupNavigationControllerForActionBar()

    }

    private fun setupActivityConfigurations() {
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        //drawerLayout = binding.mainActivityDrawerLayout
        navigationController = this.findNavController(R.id.shoeStoreNavigation)
    }

    private fun setupNavigationControllerForActionBar() {
        NavigationUI.setupActionBarWithNavController(this, navigationController)
        //NavigationUI.setupActionBarWithNavController(this, navigationController, drawerLayout)
        //NavigationUI.setupWithNavController(binding.hamburgerNavigationMenu, navigationController)

        //addNavigationControllerListener()
    }

    /*private var navigationListener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
        when(destination.id) {
            controller.graph.startDestination -> drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            else -> drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }
    }*/

    /*private fun addNavigationControllerListener() {
        navigationController.addOnDestinationChangedListener(navigationListener)
    }*/

    override fun onSupportNavigateUp(): Boolean {
        return navigationController.navigateUp()
        //return navigationController.navigateUp(drawerLayout)
    }

}
