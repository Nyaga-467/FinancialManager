package com.example.financialmanager.ui.screens


import android.content.pm.PackageManager
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.financialmanager.viewmodel.BudgetViewModel
import com.example.financialmanager.viewmodel.ThemeViewModel
import com.example.financialmanager.viewmodel.TransactionViewModel
@Composable
fun SettingsScreen(
    transactionViewModel: TransactionViewModel,
    budgetViewModel: BudgetViewModel,
    themeViewModel: ThemeViewModel
) {
    var showClearTxDialog by remember { mutableStateOf(false) }
    var showClearBudgetDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val packageManager = context.packageManager
    val packageName = context.packageName
    val version = try {
        packageManager.getPackageInfo(packageName, 0).versionName ?: "1.0"
    } catch (e: PackageManager.NameNotFoundException) {
        "1.0"
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Settings", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(24.dp))

        Text("App Info", style = MaterialTheme.typography.titleMedium)

        val isDarkMode by themeViewModel.darkMode.collectAsState()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Dark Mode")
            Switch(
                checked = isDarkMode,
                onCheckedChange = { themeViewModel.toggleDarkMode(it) }
            )
        }

        Text("App Name: Financial Manager")
        Text("Version: $version")

        Spacer(Modifier.height(24.dp))

        Text("Data Management", style = MaterialTheme.typography.titleMedium)

        Button(
            onClick = { showClearTxDialog = true },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Clear All Transactions")
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = { showClearBudgetDialog = true },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Clear All Budgets")
        }

        Spacer(Modifier.height(24.dp))


        if (showClearTxDialog) {
            AlertDialog(
                onDismissRequest = { showClearTxDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        transactionViewModel.clearAllTransactions()
                        showClearTxDialog = false
                    }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showClearTxDialog = false }) {
                        Text("Cancel")
                    }
                },
                title = { Text("Confirm Deletion") },
                text = { Text("Are you sure you want to delete all transactions? This action cannot be undone.") }
            )
        }

        if (showClearBudgetDialog) {
            AlertDialog(
                onDismissRequest = { showClearBudgetDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        budgetViewModel.clearAllBudgets()
                        showClearBudgetDialog = false
                    }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showClearBudgetDialog = false }) {
                        Text("Cancel")
                    }
                },
                title = { Text("Confirm Deletion") },
                text = { Text("Are you sure you want to delete all budgets? This action cannot be undone.") }
            )
        }
    }
}
