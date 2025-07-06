package com.example.financialmanager.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Dashboard : BottomNavItem("dashboard", "Dashboard", Icons.Default.Home)
    object Add : BottomNavItem("add", "Add", Icons.Default.Add)
    object Budgets : BottomNavItem("budgets", "Budgets", Icons.Default.PieChart)
    object Transactions : BottomNavItem("transactions", "Transactions", Icons.Default.List)
    object Settings : BottomNavItem("settings", "Settings", Icons.Default.Settings)
}
