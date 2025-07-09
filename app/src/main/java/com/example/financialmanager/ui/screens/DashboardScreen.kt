package com.example.financialmanager.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financialmanager.data.model.Transaction
import com.example.financialmanager.viewmodel.TransactionViewModel
import com.example.financialmanager.viewmodel.BudgetViewModel

@Composable
fun DashboardScreen(
    viewModel: TransactionViewModel,
    budgetViewModel: BudgetViewModel
) {
    val transactions by viewModel.transactions.collectAsState()
    val budgets by budgetViewModel.budgets.collectAsState()

    val income = transactions.filter { it.type == "income" }.sumOf { it.amount }
    val expenses = transactions.filter { it.type == "expense" }.sumOf { it.amount }
    val balance = income - expenses
    val totalBudgetAmount = budgets.sumOf { it.amount }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Dashboard", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("Hello there!", style = MaterialTheme.typography.titleMedium)
        Text("July 2025", style = MaterialTheme.typography.bodyMedium)

        Spacer(Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            SummaryCard(
                title = "Balance",
                amount = balance,
                color = Color(0xFF4F83F1),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            SummaryCard(
                title = "Income",
                amount = income,
                color = Color(0xFF4CAF50),
                modifier = Modifier.weight(1f)
            )
            SummaryCard(
                title = "Expenses",
                amount = expenses,
                color = Color(0xFFF44336),
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            SummaryCard(
                title = "Budgets",
                amount = totalBudgetAmount,
                color = Color(0xFF9C27B0), // Purple
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(16.dp))

        Text("Recent Transactions", style = MaterialTheme.typography.titleMedium)

        if (transactions.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.Default.Info, contentDescription = null)
                Spacer(Modifier.height(8.dp))
                Text("No transactions yet")
                Text("Add your first transaction to start tracking your expenses.", style = MaterialTheme.typography.bodySmall)
            }
        } else {
            LazyColumn {
                items(transactions.size) { index ->
                    val tx = transactions[index]
                    TransactionItem(tx)
                }
            }
        }
    }
}

@Composable
fun SummaryCard(title: String, amount: Double, color: Color, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = color),
        modifier = modifier
            .height(80.dp)
            .padding(end = 8.dp)
    ) {
        Column(Modifier.padding(12.dp)) {
            Text(title, color = Color.White)
            Spacer(Modifier.height(4.dp))
            Text("$${String.format("%.2f", amount)}", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun TransactionItem(tx: Transaction) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(tx.description, fontWeight = FontWeight.Bold)
                Text(tx.category, style = MaterialTheme.typography.bodySmall)
            }
            Text(
                (if (tx.type == "expense") "-$" else "+$") + String.format("%.2f", tx.amount),
                color = if (tx.type == "expense") Color.Red else Color.Green
            )
        }
    }
}
