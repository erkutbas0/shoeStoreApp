package com.udacity.shoestore.screens.authentication

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
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.screens.models.NavigationFragmentTypes
import timber.log.Timber

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModelFactory: LoginViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        viewModelFactory = LoginViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
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

    private fun gotoFragment(type: NavigationFragmentTypes) {
        when(type) {
            NavigationFragmentTypes.WELCOME -> {
                viewModel.nextPageDirected()
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            }
            else -> {
                Timber.i("missing type")
            }
        }
    }

}