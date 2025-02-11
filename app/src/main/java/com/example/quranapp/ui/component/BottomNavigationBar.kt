package com.example.quranapp.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentRoute = currentRoute(navController)

    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Quran") },
            selected = currentRoute == "quran",
            onClick = { navController.navigate("quran") },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF9B5DE5))
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.DateRange, contentDescription = "Kalender") },
            selected = currentRoute == "kalender",
            onClick = { navController.navigate("kalender") },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF9B5DE5))
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            selected = currentRoute == "profile",
            onClick = { navController.navigate("profile") },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = Color(0xFF9B5DE5))
        )
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
