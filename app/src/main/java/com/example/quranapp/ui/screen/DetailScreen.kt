package com.example.quranapp.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quranapp.R
import com.example.quranapp.ui.viewmodel.SurahViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    surahId: Int,
    viewModel: SurahViewModel = viewModel()
) {
    val surahState by viewModel.surah.observeAsState()
    val isLoading = remember { mutableStateOf(true) }
    val errorMessage = remember { mutableStateOf<String?>(null) }

    LaunchedEffect(surahId) {
        Log.d("DetailScreen", "Fetching Surah with ID: $surahId")
        try {
            viewModel.fetchSurah(surahId)
            Log.d("DetailScreen", "Successfully fetched Surah")
        } catch (e: Exception) {
            errorMessage.value = "Error fetching Surah: ${e.message}"
            Log.e("DetailScreen", "Error fetching Surah", e)
        } finally {
            isLoading.value = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Surah") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when {
                isLoading.value -> {
                    Log.d("DetailScreen", "Loading data...")
                    CircularProgressIndicator()
                }
                errorMessage.value != null -> {
                    Log.e("DetailScreen", "Error Message: ${errorMessage.value}")
                    Text("Error: ${errorMessage.value}", fontSize = 18.sp)
                }
                surahState == null -> {
                    Log.e("DetailScreen", "Surah data is null")
                    Text("Gagal memuat data", fontSize = 18.sp)
                }
                else -> {
                    surahState?.let { surah ->
                        Log.d("DetailScreen", "Displaying Surah: ${surah.name}")
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = surah.name,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Terjemahan: ${surah.translation}",
                                fontSize = 18.sp,
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            Text(
                                text = "Jumlah Ayat: ${surah.numberOfAyahs}",
                                fontSize = 16.sp,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                            val arabicFont = FontFamily(Font(R.font.amiriquranregular))
                            LazyColumn {
                                items(surah.ayahs ?: emptyList()) { ayah ->
                                    val arabText = ayah.arab.ifEmpty { "Teks tidak tersedia" }
                                    val translationText = ayah.translation.ifEmpty { "Terjemahan tidak tersedia" }

                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = CardDefaults.cardElevation(4.dp)
                                    ) {
                                        Column(modifier = Modifier.padding(16.dp)) {
                                            // Arab text (DIBUAT TEBAL dengan FONT Arabic)
                                            Text(
                                                text = arabText,
                                                fontSize = 24.sp,
                                                fontWeight = FontWeight.Bold,
                                                fontFamily = arabicFont, // Pakai font Arabic
                                                textAlign = TextAlign.Right,
                                                modifier = Modifier.fillMaxWidth()
                                            )

                                            Spacer(modifier = Modifier.height(8.dp))

                                            // Translation text (Normal)
                                            Text(
                                                text = translationText,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Normal,
                                                textAlign = TextAlign.Justify,
                                                modifier = Modifier.fillMaxWidth()
                                            )
                                        }
                                    }
                                }
                            }


                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AyahItem(number: Int, arabText: String, translationText: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Log.d("AyahItem", "Ayah Number: $number, Arab: $arabText, Translation: $translationText")

            Text(text = "$number. $arabText", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp)) // Memberi jarak
            Text(text = translationText, fontSize = 14.sp, color = Color.Gray)
        }
    }
}
