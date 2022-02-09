package com.udacity.shoestore.instrcuctions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedViewModel
import com.udacity.shoestore.databinding.FragmentInstructionsBinding
import com.udacity.shoestore.login.LoginFragmentDirections
import kotlinx.android.synthetic.main.fragment_onboarding.*

class InstructionsFragment : Fragment() {

    private lateinit var bindingInstructions: FragmentInstructionsBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingInstructions = DataBindingUtil.inflate(
            inflater, R.layout.fragment_instructions, container, false
        )
        bindingInstructions.apply {
            viewModel = sharedViewModel

            shoelistButton.setOnClickListener { v ->
                Navigation.findNavController(v).navigate(R.id.action_instructionsFragment_to_shoeListFragment)
            }
        }
        return bindingInstructions.root
    }
}