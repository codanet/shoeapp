package com.udacity.shoestore.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedViewModel
import com.udacity.shoestore.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {

    private lateinit var bindingOnboarding: FragmentOnboardingBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingOnboarding = DataBindingUtil.inflate(inflater, R.layout.fragment_onboarding, container, false)
        bindingOnboarding.apply {
            viewModel = sharedViewModel

            buttonToInstructions.setOnClickListener { v ->
                Navigation.findNavController(v).navigate(R.id.action_onboardingFragment_to_instructionsFragment)
            }
        }
        return bindingOnboarding.root
    }
}