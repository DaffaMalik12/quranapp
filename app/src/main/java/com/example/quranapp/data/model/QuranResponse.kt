package com.example.quranapp.data.model

data class SurahResponse(
    val number: Int,
    val numberOfAyahs: Int,
    val name: String,
    val translation: String,
    val revelation: String,
    val description: String,
)