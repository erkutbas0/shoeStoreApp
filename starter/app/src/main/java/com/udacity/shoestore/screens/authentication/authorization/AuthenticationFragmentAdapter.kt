package com.udacity.shoestore.screens.authentication.authorization

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.udacity.shoestore.screens.authentication.login.LoginFragment
import com.udacity.shoestore.screens.authentication.registration.RegistrationFragment
import com.udacity.shoestore.screens.authentication.protocols.ViewPagerListeners

class AuthenticationFragmentAdapter(fragment: Fragment, listener: ViewPagerListeners): FragmentStateAdapter(fragment) {

    private val loginFragment = LoginFragment(listener)
    private val registrationFragment = RegistrationFragment(listener)

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null

        fragment = when(position) {
            0 -> loginFragment
            1 -> registrationFragment
            else -> {
                loginFragment
            }
        }

        return fragment
    }

}