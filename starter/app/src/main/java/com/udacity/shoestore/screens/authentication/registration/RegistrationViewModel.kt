package com.udacity.shoestore.screens.authentication.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class RegistrationViewModel: ViewModel() {

    private var _onNextClicked = MutableLiveData<Boolean>()
    val onNextClicked: LiveData<Boolean>
        get() = _onNextClicked

    init {
        _onNextClicked.value = false
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun registrationButtonClicked() {
        Timber.i("Registration Button Clicked")
        _onNextClicked.value = true
    }

    fun nextPageDirected() {
        _onNextClicked.value = false
    }

}