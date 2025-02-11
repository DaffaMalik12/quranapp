// QuranScreen.kt
package com.example.quranapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quranapp.ui.component.BottomNavigationBar
import com.example.quranapp.ui.model.SurahInfo
import com.example.quranapp.ui.theme.purple
import com.example.quranapp.ui.viewmodel.QuranViewModel
import com.google.android.material.tabs.TabItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuranScreen(
    navController: NavController,
    viewModel: QuranViewModel = viewModel()
) {
    val surahs by viewModel.surahs.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()



    Scaffold(

        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            item {
                // Header Section
                Text(
                    text = "Quran App",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = purple,
                    modifier = Modifier.padding(top = 16.dp)
                )

                Text(
                    text = "Asslamualaikum",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Text(
                    text = "Muhammad Daffa Malik",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )

                // Last Read Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = purple),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Last Read",
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 14.sp
                            )
                            Text(
                                text = "Al-Fatihah",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            Text(
                                text = "Ayat No: 1",
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 14.sp,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                        Icon(
                            Icons.Default.MailOutline,
                            contentDescription = "Book",
                            tint = Color.White,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }

                // Loading and Error States
                when {
                    isLoading -> {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = purple)
                        }
                    }
                    error != null -> {
                        Text(
                            text = "Error: ${error}",
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }

            // Surah List with actual data
            items(surahs) { surah ->
                SurahItem(
                    SurahInfo(
                        number = surah.number,
                        name = surah.translation,
                        detail = "${surah.revelation} • ${surah.numberOfAyahs} Ayat",
                        arabicName = surah.name
                    ),
                    navController = navController // Tambahkan ini
                )
            }

        }
    }
}

@Composable
fun SurahItem(surah: SurahInfo?, navController: NavController) {
    surah?.let {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clickable {
                    navController.navigate("surahDetail/${it.number}")
                },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFF2E7FE)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it.number.toString(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF9B5DE5)
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = it.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = it.arabicName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = it.detail,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

//@Composable
//fun BottomNavigationBar(navController: NavController) {
//    NavigationBar(containerColor = Color.White) {
//        NavigationBarItem(
//            icon = { Icon(Icons.Default.Home, contentDescription = "Quran") },
//            selected = false,
//            onClick = { navController.navigate("quran") },
//            colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF9B5DE5))
//        )
//        NavigationBarItem(
//            icon = { Icon(Icons.Default.DateRange, contentDescription = "Kalender") },
//            selected = false,
//            onClick = { navController.navigate("kalender") },
//            colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF9B5DE5))
//        )
//        NavigationBarItem(
//            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
//            selected = false,
//            onClick = { navController.navigate("profile") },
//            colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF9B5DE5))
//        )
//    }
//}
