package com.example.quranapp.data.model

data class Surah(
    val number: Int,   // Menambahkan nomor surah
    val name: String,
    val translation: String,
    val numberOfAyahs: Int,
    val ayahs: List<Ayah>
)

data class Ayah(
    val number: AyahNumber,
    val arab: String,
    val translation: String
)


data class AyahNumber(
    val value: Int
)
