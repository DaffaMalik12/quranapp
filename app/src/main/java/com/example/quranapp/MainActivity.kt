package com.example.quranapp

import OnboardingScreen
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
//import com.example.quranapp.data.api.ApiService
import com.example.quranapp.data.repository.QuranRepository
import com.example.quranapp.ui.screen.DetailScreen
//import com.example.quranapp.ui.screen.OnboardingScreen
import com.example.quranapp.ui.screen.QuranScreen
import com.example.quranapp.ui.theme.QuranAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Buat instance ApiService
        val apiService = ApiService.create()

        // Buat instance QuranRepository dengan ApiService
        val repository = QuranRepository(apiService)

        setContent {
            QuranAppTheme {
                QuranApp(repository = repository)
            }
        }
    }
}

@Composable
fun QuranApp(repository: QuranRepository) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {
        composable("onboarding") {
            OnboardingScreen(navController = navController)
        }
        composable("quran") {
            QuranScreen(navController = navController)
        }
        composable(
            "SurahDetail/{surahId}",
            arguments = listOf(navArgument("surahId") { type = NavType.IntType })
        ) { backStackEntry ->
            val surahId = backStackEntry.arguments?.getInt("surahId") ?: 1
            if (surahId > 0) {
                DetailScreen(navController, surahId)
            } else {
                Log.e("MainActivity", "Invalid surah ID: $surahId")
            }
        }
    }
}
