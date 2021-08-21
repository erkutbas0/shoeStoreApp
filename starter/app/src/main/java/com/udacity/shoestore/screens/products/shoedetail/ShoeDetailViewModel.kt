package com.udacity.shoestore.screens.products.shoedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeDetailViewModel : ViewModel() {

    private var _onDoneButtonClicked = MutableLiveData<Boolean>()
    val onDoneButtonClicked: LiveData<Boolean>
        get() = _onDoneButtonClicked

    private var _onCancelButtonClicked = MutableLiveData<Boolean>()
    val onCancelButtonClicked: LiveData<Boolean>
        get() = _onCancelButtonClicked

    init {
        _onDoneButtonClicked.value = false
        _onCancelButtonClicked.value = false
    }

    override fun onCleared() {
        super.onCleared()

        _onDoneButtonClicked.value = false
        _onCancelButtonClicked.value = false

    }

    fun doneButtonClickedFired() {
        _onDoneButtonClicked.value = false
    }

    fun doneButtonClicked() {
        _onDoneButtonClicked.value = true
    }

    fun cancelButtonClickedFired() {
        _onCancelButtonClicked.value = false
    }

    fun canelButtonClicked() {
        _onCancelButtonClicked.value = true
    }

}