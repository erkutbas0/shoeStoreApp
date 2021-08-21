package com.udacity.shoestore.screens.products.shoedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.sharedModules.SharedViewModel
import com.udacity.shoestore.sharedModules.SharedViewModel2
import java.util.*
import kotlin.collections.HashMap

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private lateinit var viewModel: ShoeDetailViewModel
    private lateinit var viewModelFactory: ShoeDetailViewModelFactory

    private val model: SharedViewModel by activityViewModels()
    private val model2: SharedViewModel2 by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        setupViewModel()

        return binding.root
    }

    private fun setupViewModel() {
        viewModelFactory = ShoeDetailViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(ShoeDetailViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        addViewModelListeners()
    }

    /**
     * adding view model listeners
      */
    private fun addViewModelListeners() {
        viewModel.onCancelButtonClicked.observe(this.viewLifecycleOwner, cancelButtonObserver)
    }

    /**
     * observers
     */
    private var cancelButtonObserver = Observer<Boolean> { cancelButtonClicked ->
        if (cancelButtonClicked) {
            viewModel.cancelButtonClickedFired()
            model.takasi()
            model2.sharedViewModel2()
            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }
    }

    fun takasi() {
        var stack = Stack<Int>()
    }


}