package com.udacity.shoestore.screens.products.shoelist

import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import com.udacity.shoestore.R
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.baseModules.BaseNavigationFlows
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.screens.models.NavigationFragmentTypes
import com.udacity.shoestore.sharedModules.SharedViewModel
import com.udacity.shoestore.sharedModules.SharedViewModel2
import timber.log.Timber

class ShoeListFragment : Fragment(), BaseNavigationFlows {

    private lateinit var binding: FragmentShoeListBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationController: NavController

    private lateinit var viewModel: ShoeListViewModel
    private lateinit var viewModelFactory: ShoeListViewModelFactory

    private val model: SharedViewModel by activityViewModels()
    private var model2 = SharedViewModel2()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        setupFragmentConfigurations(inflater, container)
        initViewModel()
        setupMenuOptions()
        disableBackButton()

        //(activity as MainActivity).setupHamburgerMenuDrawer()

        /*binding.addNewItemButton.setOnClickListener {
            Timber.i("HOPPPAAAAAAA")
        }*/

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

    private fun initViewModel() {
        viewModelFactory = ShoeListViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(ShoeListViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        addViewModelListeners()
    }

    private var observer = Observer<Boolean> { addNewItemClicked ->
        if (addNewItemClicked) {
            viewModel.onAddNewItemFired()
            model.takasi()
            gotoFragment(NavigationFragmentTypes.SHOE_DETAIL)
            Timber.i("New item added clicked")
        }
    }

    private fun addViewModelListeners() {
        viewModel.onAddNewItemClicked.observe(this.viewLifecycleOwner, observer)
    }

    override fun gotoFragment(type: NavigationFragmentTypes) {
        when(type) {
            NavigationFragmentTypes.SHOE_DETAIL -> {
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
            }
            else -> {
                // nothing
            }
        }
    }


}