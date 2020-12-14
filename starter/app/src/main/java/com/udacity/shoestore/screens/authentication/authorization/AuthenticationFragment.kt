package com.udacity.shoestore.screens.authentication.authorization

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentAuthenticationBinding
import kotlinx.android.synthetic.main.fragment_authentication.*
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayout
import com.udacity.shoestore.screens.models.NavigationFragmentTypes
import timber.log.Timber
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.baseModules.BaseNavigationFlows
import com.udacity.shoestore.screens.authentication.authorization.AuthenticationFragmentDirections
import com.udacity.shoestore.screens.authentication.protocols.ViewPagerListeners

class AuthenticationFragment : Fragment(), BaseNavigationFlows {

    private lateinit var binding: FragmentAuthenticationBinding
    private lateinit var viewModel: AuthenticationViewModel
    private lateinit var viewModelFactory: AuthenticationViewModelFactory

    private lateinit var authenticationPagerAdapter: AuthenticationFragmentAdapter
    private lateinit var viewPager2: ViewPager2

    private val listener = object : ViewPagerListeners {
        override fun viewPagerOnClicked(type: NavigationFragmentTypes) {
            when(type) {
                NavigationFragmentTypes.LOGIN -> Timber.i("Login button clicked")
                NavigationFragmentTypes.REGISTRATIION -> Timber.i("Registration button clicked")
                else -> {
                    Timber.i("We do not know")
                }
            }
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_authentication, container, false)

        viewModelFactory = AuthenticationViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(AuthenticationViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        addViewModelListeners()
        enableBackButton()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authenticationPagerAdapter = AuthenticationFragmentAdapter(this, listener)
        viewPager2 = this.authenticationViewPager
        viewPager2.adapter = authenticationPagerAdapter

        TabLayoutMediator(authenticationTabLayout, viewPager2) { tab, position ->
            setTabLayoutTitles(tab, position)
            viewPager2.setCurrentItem(tab.position, true)

        }.attach()

    }

    private fun setTabLayoutTitles(tab: TabLayout.Tab, position: Int) {
        when(position) {
            0 -> tab.text = "Sign In"
            1 -> tab.text = "Sing Up"
        }
    }

    private var nextButtonObserver = Observer<Boolean> { nextPressed ->
        if (nextPressed) {
            gotoFragment(NavigationFragmentTypes.WELCOME)
        }
    }

    private fun addViewModelListeners() {
        viewModel.onTestClicked.observe(this.viewLifecycleOwner, nextButtonObserver)
    }

    override fun gotoFragment(type: NavigationFragmentTypes) {
        when(type) {
            NavigationFragmentTypes.WELCOME -> {
                viewModel.nextPageDirected()
                Timber.i("Ahanda buradayiz")
            }
            else -> {
                Timber.i("missing type")
            }
        }
    }

    private fun enableBackButton() {
        requireActivity().onBackPressedDispatcher.addCallback {
            activity?.finish()
        }
    }

}