package com.example.quranapp.data.network

import com.example.quranapp.data.model.PrayerScheduleResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SholatApiService {
    @GET("sholat/jadwal/{city_id}/{year}/{month}")
    suspend fun getPrayerSchedule(
        @Path("city_id") cityId: Int,
        @Path("year") year: Int,
        @Path("month") month: Int
    ): PrayerScheduleResponse

    companion object {
        private const val BASE_URL = "https://api.myquran.com/v2/"

        fun create(): SholatApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SholatApiService::class.java)
        }
    }
}
