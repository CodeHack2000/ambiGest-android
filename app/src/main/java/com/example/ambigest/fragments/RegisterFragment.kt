package com.example.ambigest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ambigest.R
import com.example.ambigest.databinding.FragmentRegisterBinding
import com.example.ambigest.viewModel.AuthViewModel

class RegisterFragment : Fragment() {
    private var binding: FragmentRegisterBinding ?= null
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            registerFragment = this@RegisterFragment
            viewModel = authViewModel
        }
    }

    fun redirectToLoginFragment() {
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}