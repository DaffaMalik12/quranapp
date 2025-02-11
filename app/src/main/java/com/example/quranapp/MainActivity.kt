package com.example.quranapp

import OnboardingScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.quranapp.data.preferences.UserPreferences
import com.example.quranapp.data.repository.QuranRepository
import com.example.quranapp.ui.screen.*
import com.example.quranapp.ui.theme.QuranAppTheme
import com.example.quranapp.viewmodel.AuthViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = ApiService.create()
        val repository = QuranRepository(apiService)

        lifecycleScope.launch {
            val hasSeenOnboarding = UserPreferences(this@MainActivity).getOnboardingStatus().first()

            runOnUiThread {
                setContent {
                    QuranAppTheme {
                        QuranApp(repository = repository, hasSeenOnboarding)
                    }
                }
            }
        }

    }
}

@Composable
fun QuranApp(repository: QuranRepository, hasSeenOnboarding: Boolean) {
    val navController = rememberNavController()

    val startDestination = if (hasSeenOnboarding) "login" else "onboarding"

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("onboarding") {
            OnboardingScreen(navController)
        }
        composable("login") {
            val authViewModel = remember { AuthViewModel() } // Tambahkan ViewModel
            LoginScreen(navController, authViewModel)
        }
        composable("register") {
            val  authViewModel = remember { AuthViewModel() }
            RegisterScreen(navController, authViewModel)
        }
        composable("quran") {
            QuranScreen(navController)
        }
        composable("kalender") {
            KalenderScreen(navController)
        }
        composable("profile") {
            ProfileScreen(navController)
        }
        composable(
            "SurahDetail/{surahId}",
            arguments = listOf(navArgument("surahId") { type = NavType.IntType })
        ) { backStackEntry ->
            val surahId = backStackEntry.arguments?.getInt("surahId") ?: 1
            DetailScreen(navController, surahId)
        }
    }
}
