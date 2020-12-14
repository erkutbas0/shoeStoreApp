package com.udacity.shoestore.screens.authentication.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class AuthenticationViewModel: ViewModel() {

    private var _onTestClicked = MutableLiveData<Boolean>()
    val onTestClicked: LiveData<Boolean>
        get() = _onTestClicked

    init {
        _onTestClicked.value = false
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun loginButtonClicked() {
        _onTestClicked.value = true
    }

    fun nextPageDirected() {
        _onTestClicked.value = false
    }

}