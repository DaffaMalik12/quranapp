package com.example.quranapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
//import com.example.quranapp.data.model.AyahNumber
import com.example.quranapp.data.model.Surah

//import com.example.quranapp.data.model.Surah

class SurahViewModel : ViewModel() {

    // Inisialisasi apiService
    private val apiService = ApiService.create()

    // LiveData untuk menyimpan data Surah
    private val _surah = MutableLiveData<Surah?>()
    val surah: LiveData<Surah?> = _surah

    // Fetch data Surah berdasarkan ID
    fun fetchSurah(id: Int) {
        viewModelScope.launch {
            try {
                val response = apiService.getSurah(id)
                if (response != null) {
                    _surah.postValue(response)
                } else {
                    _surah.postValue(null)
                    Log.e("SurahViewModel", "Response is null for surah ID: $id")
                }
            } catch (e: Exception) {
                _surah.postValue(null)
                Log.e("SurahViewModel", "Error fetching surah: ${e.message}")
            }
        }
    }
}
