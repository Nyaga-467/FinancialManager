package com.example.financialmanager


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.financialmanager.navigation.AppNavGraph
import com.example.financialmanager.navigation.BottomNavItem
import com.example.financialmanager.ui.components.BottomNavigationBar
import com.example.financialmanager.ui.theme.FinancialManagerTheme
import com.example.financialmanager.viewmodel.BudgetViewModel
import com.example.financialmanager.viewmodel.ThemeViewModel
import com.example.financialmanager.viewmodel.TransactionViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // ViewModels
            val transactionViewModel: TransactionViewModel = viewModel()
            val budgetViewModel: BudgetViewModel = viewModel()
            val themeViewModel: ThemeViewModel = viewModel()

            // Navigation
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route ?: BottomNavItem.Dashboard.route

            // Observe dark mode
            val isDarkMode by themeViewModel.darkMode.collectAsState()

            FinancialManagerTheme(darkTheme = isDarkMode) {
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            navController = navController,
                            currentRoute = currentRoute
                        )
                    }
                ) { padding ->
                    AppNavGraph(
                        navController = navController,
                        modifier = Modifier.padding(padding),
                        transactionViewModel = transactionViewModel,
                        budgetViewModel = budgetViewModel,
                        themeViewModel = themeViewModel
                    )
                }
            }
        }
    }
}
