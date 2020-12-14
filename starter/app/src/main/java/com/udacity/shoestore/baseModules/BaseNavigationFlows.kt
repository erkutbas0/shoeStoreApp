package com.udacity.shoestore.baseModules

import com.udacity.shoestore.screens.models.NavigationFragmentTypes

interface BaseNavigationFlows {

    fun gotoFragment(type: NavigationFragmentTypes)
}