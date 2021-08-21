package com.udacity.shoestore.sharedModules

import androidx.lifecycle.ViewModel
import timber.log.Timber

class SharedViewModel: ViewModel() {

    fun takasi() {
        Timber.i("Shared viewmodel takasi fired")
    }

}