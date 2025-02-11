package com.example.quranapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.quranapp.R
import com.example.quranapp.ui.component.BottomNavigationBar

@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }  // Tambahkan BottomNavigationBar
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)  // Tambahkan padding agar tidak tertutup bottom bar
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Foto Profil
            Image(
                painter = painterResource(id = R.drawable.onboarding_image),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nama dan Email
            Text(text = "Muhammad Daffa", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = "daffa@example.com", fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)

            Spacer(modifier = Modifier.height(24.dp))

            // Menu Profile
            ProfileMenuItem(title = "Edit Profile") { /* Aksi Edit Profile */ }

            ProfileMenuItem(title = "Logout") {
                navController.navigate("login") // Contoh kembali ke onboarding setelah logout
            }
        }
    }
}

@Composable
fun ProfileMenuItem(title: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(text = title)
    }
}
