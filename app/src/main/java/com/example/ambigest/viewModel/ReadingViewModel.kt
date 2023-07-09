package com.example.ambigest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ambigest.model.ReadingsModel

class ReadingViewModel: ViewModel() {
    private var _contractExists = MutableLiveData<Boolean>()
    val contractExists: LiveData<Boolean> = _contractExists

    private var _nextReadingDate = MutableLiveData<String>()
    val nextReadingDate: LiveData<String> = _nextReadingDate

    private var _readingsDataset: MutableLiveData<List<ReadingsModel>> = MutableLiveData()
    val readingsDataset: LiveData<List<ReadingsModel>> = _readingsDataset

    init {
        setContractExists(false)
        setNextReadingDate("")
        setTestReadingsDataset()
    }

    fun setContractExists(value: Boolean) {
        _contractExists.value = value
    }

    fun setNextReadingDate(value: String) {
        _nextReadingDate.value = value
    }

    fun setReadingsDataset(dataset: List<ReadingsModel>) {
        _readingsDataset.value = dataset
    }

    // TODO: This is only TEST function, when the project connects with back-end we need to delete this function
    private fun setTestReadingsDataset() {
        val dataset = listOf(
            ReadingsModel(
                "2023-07-09",
                122.0,
                "test1"
            ),
            ReadingsModel(
                "2023-08-09",
                126.0,
                "test2"
            ),
            ReadingsModel(
                "2023-09-09",
                145.6,
                "test3"
            ),
            ReadingsModel(
                "2023-10-09",
                167.4,
                "test4"
            ),
            ReadingsModel(
                "2023-11-09",
                144.0,
                "test5"
            ),
            ReadingsModel(
                "2023-12-09",
                112.0,
                "test6"
            ),
        )

        _readingsDataset.value = dataset
    }
}