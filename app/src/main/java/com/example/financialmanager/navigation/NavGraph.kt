package com.example.financialmanager.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.financialmanager.ui.screens.*
import com.example.financialmanager.viewmodel.BudgetViewModel
import com.example.financialmanager.viewmodel.ThemeViewModel
import com.example.financialmanager.viewmodel.TransactionViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    transactionViewModel: TransactionViewModel,
    budgetViewModel: BudgetViewModel,
    themeViewModel: ThemeViewModel  // ✅ Add this line
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Dashboard.route,
        modifier = modifier
    ) {
        composable(BottomNavItem.Dashboard.route) {
            DashboardScreen(transactionViewModel)
        }
        composable(BottomNavItem.Add.route) {
            AddTransactionScreen(transactionViewModel)
        }
        composable(BottomNavItem.Budgets.route) {
            AddBudgetScreen(budgetViewModel)
        }
        composable(BottomNavItem.Transactions.route) {
            TransactionListScreen(transactionViewModel)
        }
        composable(BottomNavItem.Settings.route) {
            SettingsScreen(
                transactionViewModel = transactionViewModel,
                budgetViewModel = budgetViewModel,
                themeViewModel = themeViewModel  // ✅ Pass it in here
            )
        }
    }
}
