package com.example.quranapp.di
import ApiService
import com.example.quranapp.data.repository.QuranRepository
import dagger.Module
import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Provides
//    @Singleton
//    fun provideQuranApiService(): ApiService {
//        return Retrofit.Builder()
//            .baseUrl("https://quran-api-id.vercel.app/") // URL API
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiService::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun provideQuranRepository(apiService: ApiService): QuranRepository {
//        return QuranRepository(apiService)
//    }
//}
