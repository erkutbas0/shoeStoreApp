package com.udacity.shoestore.screens.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.baseModules.BaseNavigationFlows
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.screens.models.NavigationFragmentTypes
import timber.log.Timber

class WelcomeFragment : Fragment(), BaseNavigationFlows {

    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var viewModel: WelcomeViewModel
    private lateinit var viewModelFactory: WelcomeViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)

        viewModelFactory = WelcomeViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(WelcomeViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        addViewModelListeners()

        return binding.root
    }

    private var nextButtonObserver = Observer<Boolean> { nextPressed ->
        if (nextPressed) {
            gotoFragment(NavigationFragmentTypes.INSTRUCTION)
        }
    }

    private fun addViewModelListeners() {
        viewModel.onNextClicked.observe(this.viewLifecycleOwner, nextButtonObserver)
    }

    override fun gotoFragment(type: NavigationFragmentTypes) {
        when(type) {
            NavigationFragmentTypes.INSTRUCTION -> {
                viewModel.nextPageDirected()
                findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment())
            }
            else -> {
                Timber.i("missing type")
            }
        }
    }
}