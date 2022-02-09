package com.udacity.shoestore.shoedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.databinding.FragmentShoeListBinding

class ShoeDetailsFragment : Fragment() {

    private lateinit var bindingShoeDetails: FragmentShoeDetailsBinding
    private lateinit var navController: NavController
    private val sharedViewModel: SharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingShoeDetails = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_details, container, false
        )
        navController = findNavController()

        bindingShoeDetails.apply {
            viewModel = sharedViewModel
            shoe = sharedViewModel.currentShoe.value

            cancelButton.setOnClickListener {
                returnToShoeList()
            }

            sharedViewModel.addedShoeEvent.observe(viewLifecycleOwner, Observer { shoeAdded ->
                if (shoeAdded) {
                    sharedViewModel.completedAddShoe()
                    returnToShoeList()
                }
            })
        }

        return bindingShoeDetails.root
    }

    private fun returnToShoeList() {
        navController.navigate(R.id.action_shoeDetailsFragment_to_shoeListFragment)
    }


}