package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navigationController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActivityConfigurations()
        setupNavigationControllerForActionBar()
    }

    private fun setupActivityConfigurations() {
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        navigationController = this.findNavController(R.id.shoeStoreNavigation)
    }

    private fun setupNavigationControllerForActionBar() {
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.authenticationFragment,
                R.id.welcomeFragment,
                R.id.instructionFragment,
                R.id.shoeListFragment
            )
        )
        NavigationUI.setupActionBarWithNavController(
            this,
            navigationController,
            appBarConfiguration
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigationController.navigateUp(appBarConfiguration)
    }

}
