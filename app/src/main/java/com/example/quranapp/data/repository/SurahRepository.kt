package com.example.quranapp.data.repository

import ApiService
import com.example.quranapp.data.model.Surah

class SurahRepository(private val apiService: ApiService) {

    suspend fun getSurahById(surahId: Int): Surah? {
        return try {
            apiService.getSurah(surahId) // Pastikan API tidak error
        } catch (e: Exception) {
            println("API error: ${e.message}")
            null
        }
    }
}