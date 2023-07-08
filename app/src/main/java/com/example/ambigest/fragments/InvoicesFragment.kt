package com.example.ambigest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ambigest.R
import com.example.ambigest.adaptor.InvoicesAdaptor
import com.example.ambigest.databinding.FragmentInvoicesBinding
import com.example.ambigest.viewModel.InvoicesViewModel

class InvoicesFragment : Fragment() {
    private var binding: FragmentInvoicesBinding ?= null
    private val invoicesViewModel: InvoicesViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInvoicesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = invoicesViewModel
        }

        binding?.dashboardNavigation?.selectedItemId = R.id.page_2
        binding?.dashboardNavigation?.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.page_1 -> {
                    findNavController().navigate(R.id.action_invoicesFragment_to_dashboardFragment)
                    true
                }
                R.id.page_3 -> {
                    true
                }
                R.id.page_4 -> {
                    true
                }
                else -> false
            }
        }

        recyclerView = binding?.invoicesRv!!
        invoicesViewModel.invoicesDataset.observe(viewLifecycleOwner) {dataset ->
            recyclerView.adapter = InvoicesAdaptor(dataset)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}