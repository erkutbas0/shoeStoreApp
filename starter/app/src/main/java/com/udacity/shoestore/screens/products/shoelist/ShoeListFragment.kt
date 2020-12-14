package com.udacity.shoestore.screens.products.shoelist

import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import com.udacity.shoestore.R
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.databinding.FragmentShoeListBinding

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationController: NavController

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        setupFragmentConfigurations(inflater, container)
        setupNavigationControllerForActionBar()
        setupMenuOptions()
        disableBackButton()

        //(activity as MainActivity).setupHamburgerMenuDrawer()

        return binding.root
    }

    private fun setupMenuOptions() {
        this.setHasOptionsMenu(true)
    }

    private fun setupFragmentConfigurations(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        drawerLayout = binding.mainActivityDrawerLayout
        navigationController = this.findNavController()
    }

    private fun setupNavigationControllerForActionBar() {
        NavigationUI.setupWithNavController(binding.shoeListHamburgerMenu, navigationController)
        addNavigationControllerListener()
    }

    private var navigationListener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
        when(destination.id) {
            controller.graph.startDestination -> drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            else -> drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }
    }

    private fun addNavigationControllerListener() {
        navigationController.addOnDestinationChangedListener(navigationListener)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, findNavController()) || super.onOptionsItemSelected(item)
    }

    private fun disableBackButton() {
        requireActivity().onBackPressedDispatcher.addCallback {
            activity?.finish()
        }
    }

}