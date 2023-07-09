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
import com.example.ambigest.adaptor.ReadingsAdaptor
import com.example.ambigest.databinding.FragmentReadingsBinding
import com.example.ambigest.viewModel.ReadingViewModel

class ReadingsFragment : Fragment() {
    private var binding: FragmentReadingsBinding ?= null
    private val readingViewModel: ReadingViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReadingsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = readingViewModel
        }

        binding?.readingsNavigation?.selectedItemId = R.id.page_4
        binding?.readingsNavigation?.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.page_1 -> {
                    findNavController().navigate(R.id.action_readingsFragment_to_dashboardFragment)
                    true
                }
                R.id.page_2 -> {
                    findNavController().navigate(R.id.action_readingsFragment_to_invoicesFragment)
                    true
                }
                R.id.page_3 -> {
                    true
                }
                else -> false
            }
        }

        recyclerView = binding?.readingsRv!!
        readingViewModel.readingsDataset.observe(viewLifecycleOwner) {dataset ->
            recyclerView.adapter = ReadingsAdaptor(dataset)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}