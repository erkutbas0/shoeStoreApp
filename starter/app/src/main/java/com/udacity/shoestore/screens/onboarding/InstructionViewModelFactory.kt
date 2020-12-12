package com.udacity.shoestore.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class InstructionViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InstructionViewModel::class.java)) {
            return InstructionViewModel() as T
        }

        throw IllegalArgumentException("Wrong viewmodel class")
    }
}