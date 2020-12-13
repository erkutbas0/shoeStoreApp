package com.udacity.shoestore.screens.authentication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlin.math.log

class AuthenticationFragmentAdapter(fragment: Fragment, denemeLog: DenemeLog): FragmentStateAdapter(fragment) {

    val loginFragment = LoginFragment(denemeLog)
    val registrationFragment = RegistrationFragment()

    private var zoko = denemeLog

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

/*
class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        if (position == 0) {
            fragment = FragmentOne()
        } else if (position == 1) {
            fragment = FragmentTwo()
        } else if (position == 2) {
            fragment = FragmentThree()
        }
        return fragment
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        if (position == 0) {
            title = "First"
        } else if (position == 1) {
            title = "Second"
        } else if (position == 2) {
            title = "Third"
        }
        return title
    }
}


 */