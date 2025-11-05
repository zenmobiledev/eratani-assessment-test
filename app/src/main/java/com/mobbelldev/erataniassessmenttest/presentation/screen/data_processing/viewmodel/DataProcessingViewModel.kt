package com.mobbelldev.erataniassessmenttest.presentation.screen.data_processing.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobbelldev.erataniassessmenttest.presentation.screen.data_processing.model.Item
import com.mobbelldev.erataniassessmenttest.presentation.screen.data_processing.repository.AgricultureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DataProcessingViewModel(private val repository: AgricultureRepository) : ViewModel() {
    private val _dataList = MutableStateFlow<List<Item>>(value = emptyList())
    val dataList = _dataList.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            _dataList.value = repository.loadDataFromJson()
        }
    }
}