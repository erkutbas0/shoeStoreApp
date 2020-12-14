package com.udacity.shoestore.screens.authentication.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class LoginViewModel: ViewModel() {

    private var _onNextClicked = MutableLiveData<Boolean>()
    val onNextClicked: LiveData<Boolean>
    get() = _onNextClicked

    init {
        _onNextClicked.value = false
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun loginButtonClicked() {
        Timber.i("Login Button Clicked")
        _onNextClicked.value = true
    }

    fun nextPageDirected() {
        _onNextClicked.value = false
    }

}