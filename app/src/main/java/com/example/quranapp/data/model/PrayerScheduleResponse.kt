package com.example.quranapp.data.model

data class PrayerScheduleResponse(
    val data: PrayerData
)

data class PrayerData(
    val jadwal: List<PrayerTime>
)

data class PrayerTime(
    val tanggal: String,
    val imsak: String,
    val subuh: String,
    val dzuhur: String,
    val ashar: String,
    val maghrib: String,
    val isya: String
)
