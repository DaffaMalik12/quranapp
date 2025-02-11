package com.example.quranapp.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quranapp.ui.component.BottomNavigationBar
import com.example.quranapp.ui.theme.purple
import com.example.quranapp.viewmodel.SholatViewModel

@Composable
fun KalenderScreen(navController: NavController, sholatViewModel: SholatViewModel = viewModel()) {
    val prayerSchedule by sholatViewModel.prayerSchedule.collectAsState()



    LaunchedEffect(Unit) {
        sholatViewModel.fetchPrayerSchedule(1631, 2025, 2) // Contoh: Jakarta, Juni 2025
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {

            Text(
                text = "Jadwal Sholat",
                fontWeight = FontWeight.Bold,
                color = purple,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.primary)

            Spacer(modifier = Modifier.height(16.dp))

            when {
                prayerSchedule == null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                prayerSchedule?.data?.jadwal.isNullOrEmpty() -> {
                    Text(
                        text = "Jadwal sholat tidak tersedia.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(prayerSchedule!!.data.jadwal) { prayer ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                elevation = CardDefaults.cardElevation(4.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        text = "📅 Tanggal: ${prayer.tanggal}",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))

                                    Row {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(text = "🌅 Imsak: ${prayer.imsak}")
                                            Text(text = "🕌 Subuh: ${prayer.subuh}")
                                            Text(text = "☀️ Dzuhur: ${prayer.dzuhur}")
                                        }
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(text = "🌇 Ashar: ${prayer.ashar}")
                                            Text(text = "🌆 Maghrib: ${prayer.maghrib}")
                                            Text(text = "🌙 Isya: ${prayer.isya}")
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
