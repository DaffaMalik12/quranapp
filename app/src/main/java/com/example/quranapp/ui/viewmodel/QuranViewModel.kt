// QuranViewModel.kt
package com.example.quranapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quranapp.data.model.SurahResponse
//import com.example.quranapp.data.api.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuranViewModel : ViewModel() {
    private val apiService = ApiService.create()

    private val _surahs = MutableStateFlow<List<SurahResponse>>(emptyList())
    val surahs: StateFlow<List<SurahResponse>> = _surahs

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadSurahs()
    }

    private fun loadSurahs() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _surahs.value = apiService.getSurahs()
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}