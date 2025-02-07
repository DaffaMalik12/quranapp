package com.example.quranapp.data.repository

import ApiService
import com.example.quranapp.data.model.Surah
import com.example.quranapp.data.model.SurahResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
//import javax.inject.Inject

class QuranRepository(private val apiService: ApiService) {
    suspend fun getSurahs(): List<SurahResponse> {
        return withContext(Dispatchers.IO) {
            apiService.getSurahs()
        }
    }


}