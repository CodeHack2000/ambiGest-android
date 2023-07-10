package com.example.ambigest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ambigest.model.InvoicesModel

class InvoicesViewModel: ViewModel() {
    private var _contractPrice = MutableLiveData<String>()
    val contractPrice: LiveData<String> = _contractPrice

    private var _contractBtnText = MutableLiveData<String>()
    val contractBtnText: LiveData<String> = _contractBtnText

    private var _invoicesDataset: MutableLiveData<List<InvoicesModel>> = MutableLiveData()
    val invoicesDataset: LiveData<List<InvoicesModel>> = _invoicesDataset

    init {
        setContractPrice("0")
        setTestInvoicesDataset()
    }

    fun setContractPrice(value: String) {
        _contractPrice.value = value
        setContractBtnText()
    }

    fun setContractBtnText() {
        if (_contractPrice.value?.toInt()!! <= 0)
            _contractBtnText.value = "Criar Contrato"
        else
            _contractBtnText.value = "Alterar Contrato"
    }

    fun setInvoicesDataset(dataset: List<InvoicesModel>) {
        _invoicesDataset.value = dataset
    }

    // TODO: This is only TEST function, when the project connects with back-end we need to delete this function
    private fun setTestInvoicesDataset() {
        val dataset = listOf(
            InvoicesModel(
                "July",
                124.0,
                -3.0,
                3100.0
            ),
            InvoicesModel(
                "August",
                133.0,
                9.0,
                3325.0
            ),
            InvoicesModel(
                "July",
                124.0,
                -3.0,
                3100.0
            ),
            InvoicesModel(
                "August",
                133.0,
                9.0,
                3325.0
            ),
            InvoicesModel(
                "July",
                124.0,
                -3.0,
                3100.0
            ),
            InvoicesModel(
                "August",
                133.0,
                9.0,
                3325.0
            ),
            InvoicesModel(
                "July",
                124.0,
                -3.0,
                3100.0
            ),
            InvoicesModel(
                "August",
                133.0,
                9.0,
                3325.0
            ),
        )

        _invoicesDataset.value = dataset
    }
}