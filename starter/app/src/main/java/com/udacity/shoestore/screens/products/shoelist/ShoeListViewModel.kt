package com.udacity.shoestore.screens.products.shoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListViewModel: ViewModel() {

    private var _onAddNewItemClicked = MutableLiveData<Boolean>()
    val onAddNewItemClicked: LiveData<Boolean>
        get() = _onAddNewItemClicked

    init {
        _onAddNewItemClicked.value = false
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun onAddNewItemFired() {
        _onAddNewItemClicked.value = false
    }

    fun addNewItemClicked() {
        _onAddNewItemClicked.value = true
    }

}