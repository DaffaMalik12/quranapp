package com.example.quranapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quranapp.data.model.PrayerScheduleResponse
import com.example.quranapp.data.network.SholatApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SholatViewModel : ViewModel() {
    private val sholatApi = SholatApiService.create()

    private val _prayerSchedule = MutableStateFlow<PrayerScheduleResponse?>(null)
    val prayerSchedule: StateFlow<PrayerScheduleResponse?> = _prayerSchedule

    fun fetchPrayerSchedule(cityId: Int, year: Int, month: Int) {
        viewModelScope.launch {
            try {
                _prayerSchedule.value = sholatApi.getPrayerSchedule(cityId, year, month)
            } catch (e: Exception) {
                Log.e("SholatViewModel", "Error fetching prayer schedule: ${e.message}")
            }
        }
    }
}
