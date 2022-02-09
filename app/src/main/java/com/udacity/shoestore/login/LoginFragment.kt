package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedViewModel
import com.udacity.shoestore.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var bindingLogin: FragmentLoginBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingLogin = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )
        bindingLogin.apply {
            viewModel = sharedViewModel

            loginButton.setOnClickListener {tryLogin()}
            signupButton.setOnClickListener {tryLogin()}
        }

        return bindingLogin.root
    }
    private fun tryLogin() {
        var canLogin = true

        if (bindingLogin.loginText.text.toString().isEmpty()) {
            bindingLogin.loginText.error = "Please enter your email."
            canLogin = false
        }

        if (bindingLogin.passwordText.text.toString().isEmpty()) {
            bindingLogin.passwordText.error = "Please enter your password."
            canLogin = false
        }

        if (canLogin) {
            navigateToOnboarding()
        }
    }

    private fun navigateToOnboarding() {
        sharedViewModel.login()
        findNavController().navigate(R.id.action_loginFragment_to_onboardingFragment)
    }
}