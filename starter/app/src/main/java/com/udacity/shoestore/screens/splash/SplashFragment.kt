package com.udacity.shoestore.screens.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentSplashBinding
import com.udacity.shoestore.screens.models.NavigationFragmentTypes
import timber.log.Timber
import androidx.lifecycle.Observer
import com.udacity.shoestore.baseModules.BaseNavigationFlows

class SplashFragment : Fragment(), BaseNavigationFlows {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var viewModel: SplashViewModel
    private lateinit var viewModelFactory: SplashViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)

        viewModelFactory = SplashViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(SplashViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        addViewModelListeners()

        return binding.root
    }

    private var nextButtonObserver = Observer<Boolean> { nextPressed ->
        if (nextPressed) {
            gotoFragment(NavigationFragmentTypes.LOGIN)
        }
    }

    private fun addViewModelListeners() {
        viewModel.onNextClicked.observe(this.viewLifecycleOwner, nextButtonObserver)
    }

    override fun gotoFragment(type: NavigationFragmentTypes) {
        when(type) {
            NavigationFragmentTypes.LOGIN -> {
                viewModel.nextPageDirected()
                //findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
            }
            else -> {
                Timber.i("missing type")
            }
        }
    }

}