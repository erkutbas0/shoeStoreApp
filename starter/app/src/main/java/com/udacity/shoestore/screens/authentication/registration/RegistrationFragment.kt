package com.udacity.shoestore.screens.authentication.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.baseModules.BaseNavigationFlows
import com.udacity.shoestore.databinding.FragmentRegistrationBinding
import com.udacity.shoestore.screens.authentication.authorization.AuthenticationFragmentDirections
import com.udacity.shoestore.screens.authentication.protocols.ViewPagerListeners
import com.udacity.shoestore.screens.models.NavigationFragmentTypes
import timber.log.Timber
import androidx.lifecycle.Observer

class RegistrationFragment(private val listener: ViewPagerListeners) : Fragment(), BaseNavigationFlows {

    private lateinit var binding: FragmentRegistrationBinding
    private lateinit var viewModel: RegistrationViewModel
    private lateinit var viewModelFactory: RegistrationViewModelFactory

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_registration, container, false)

        viewModelFactory = RegistrationViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(RegistrationViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        addViewModelListeners()

        return binding.root
    }

    private var nextButtonObserver = Observer<Boolean> { nextPressed ->
        if (nextPressed) {
            gotoFragment(NavigationFragmentTypes.WELCOME)
        }
    }

    private fun addViewModelListeners() {
        viewModel.onNextClicked.observe(this.viewLifecycleOwner, nextButtonObserver)
    }

    override fun gotoFragment(type: NavigationFragmentTypes) {
        when(type) {
            NavigationFragmentTypes.WELCOME -> {
                viewModel.nextPageDirected()
                listener.viewPagerOnClicked(NavigationFragmentTypes.REGISTRATIION)
                findNavController().navigate(AuthenticationFragmentDirections.actionAuthenticationFragmentToWelcomeFragment())
            }
            else -> {
                Timber.i("missing type")
            }
        }
    }
}