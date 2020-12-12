package com.udacity.shoestore.screens.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionBinding
import com.udacity.shoestore.screens.models.NavigationFragmentTypes
import timber.log.Timber

class InstructionFragment : Fragment() {

    private lateinit var viewModel: InstructionViewModel
    private lateinit var viewModelFactory: InstructionViewModelFactory
    private lateinit var binding: FragmentInstructionBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_instruction, container,
        false)

        viewModelFactory = InstructionViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(InstructionViewModel::class.java)
        binding.viewModel = viewModel

        addViewModelListeners()

        return binding.root
    }

    private var nextButtonObserver = Observer<Boolean> { nextPressed ->
        if (nextPressed) {
            gotoFragment(NavigationFragmentTypes.SHOE_LIST)
        }
    }

    private fun addViewModelListeners() {
        viewModel.onNextClicked.observe(this.viewLifecycleOwner, nextButtonObserver)
    }

    private fun gotoFragment(type: NavigationFragmentTypes) {
        when(type) {
            NavigationFragmentTypes.SHOE_LIST -> {
                viewModel.nextPageDirected()
                findNavController().navigate(InstructionFragmentDirections.actionInstructionFragmentToShoeList())
            }
            else -> {
                Timber.i("missing type")
            }
        }
    }

}