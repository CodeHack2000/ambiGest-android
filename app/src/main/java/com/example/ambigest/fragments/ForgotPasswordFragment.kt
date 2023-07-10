package com.example.ambigest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ambigest.R
import com.example.ambigest.databinding.FragmentForgotPasswordBinding
import com.example.ambigest.viewModel.AuthViewModel

class ForgotPasswordFragment : Fragment() {
    private var binding: FragmentForgotPasswordBinding ?= null
    private val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            forgotPasswordFragment = this@ForgotPasswordFragment
            viewModel = authViewModel
        }
    }

    // TODO: This function needs to be connected with backend
    fun recoverUserPassword() {
        findNavController().navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}