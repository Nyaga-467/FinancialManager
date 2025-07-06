package com.example.financialmanager.ui.components


import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.financialmanager.navigation.BottomNavItem

@Composable
fun BottomNavigationBar(navController: NavController, currentRoute: String) {
    NavigationBar {
        val items = listOf(
            BottomNavItem.Dashboard,
            BottomNavItem.Add,
            BottomNavItem.Budgets,
            BottomNavItem.Transactions,
            BottomNavItem.Settings
        )

        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}
