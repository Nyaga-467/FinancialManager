package com.example.financialmanager.ui.screens



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.financialmanager.data.model.Transaction
import com.example.financialmanager.viewmodel.TransactionViewModel

@Composable
fun TransactionListScreen(viewModel: TransactionViewModel) {
    val transactions by viewModel.transactions.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text(
            text = "All Transactions",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (transactions.isEmpty()) {
            Text(
                text = "No transactions recorded.",
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(transactions) { tx ->
                    TransactionCard(tx)
                }
            }
        }
    }
}

@Composable
fun TransactionCard(tx: Transaction) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = tx.description, fontWeight = FontWeight.Bold)
                Text(text = tx.category, style = MaterialTheme.typography.bodySmall)
            }
            Text(
                text = (if (tx.type == "expense") "-$" else "+$") + String.format("%.2f", tx.amount),
                color = if (tx.type == "expense") MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
            )
        }
    }
}
