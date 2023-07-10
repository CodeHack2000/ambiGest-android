package com.example.ambigest.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ambigest.R
import com.example.ambigest.databinding.FragmentLoginBinding
import com.example.ambigest.viewModel.AuthViewModel

class LoginFragment : Fragment() {
    private var binding: FragmentLoginBinding ?= null
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            loginFragment = this@LoginFragment
            viewModel = authViewModel
        }

        authViewModel.isLoggedIn.observe(viewLifecycleOwner) {isLoggedIn ->
            if (isLoggedIn) {
                val token = authViewModel.authToken.value
                if (!token.isNullOrEmpty()) {
                    saveAuthToken(token)

                    findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
                }
            }
        }

        binding?.loginEtEmail?.addTextChangedListener { text ->
            authViewModel.setEmail(text.toString())
        }

        binding?.loginEtPassword?.addTextChangedListener { text ->
            authViewModel.setPassword(text.toString())
        }
    }

    private fun saveAuthToken(token: String) {
        val PREF_FILE_NAME = "auth_pref"
        val PREF_TOKEN_KEY = "auth.token"
        val sharedPreferences = requireContext().getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(PREF_TOKEN_KEY, token).apply()
    }

    fun redirectToRegisterFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    fun redirectToForgotPasswordFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
    }

    fun TESTredirectToDashboardFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}