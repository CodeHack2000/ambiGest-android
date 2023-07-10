package com.example.ambigest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ambigest.MainActivity
import com.example.ambigest.R
import com.example.ambigest.databinding.FragmentDashboardBinding
import com.example.ambigest.viewModel.AuthViewModel

class DashboardFragment : Fragment() {
    private var binding: FragmentDashboardBinding ?= null
    private val authViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (requireActivity() is MainActivity) {
            val mainActivity = requireActivity() as MainActivity
            mainActivity.showMenuItems()
        }

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = authViewModel
        }

        authViewModel.reinitializaData()

        binding?.dashboardNavigation?.selectedItemId = R.id.page_1
        binding?.dashboardNavigation?.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.page_2 -> {
                    findNavController().navigate(R.id.action_dashboardFragment_to_invoicesFragment)
                    true
                }
                R.id.page_3 -> {
                    true
                }
                R.id.page_4 -> {
                    findNavController().navigate(R.id.action_dashboardFragment_to_readingsFragment)
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}