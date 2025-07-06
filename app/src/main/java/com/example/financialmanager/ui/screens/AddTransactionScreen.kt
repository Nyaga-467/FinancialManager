@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.financialmanager.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financialmanager.data.model.Transaction
import com.example.financialmanager.viewmodel.TransactionViewModel
import kotlinx.coroutines.launch

@Composable
fun AddTransactionScreen(viewModel: TransactionViewModel) {
    var selectedType by remember { mutableStateOf("expense") }
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Food") }

    val categories = listOf("Food", "Transport", "Shopping", "Health", "Entertainment", "Bills")
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Add", fontSize = 22.sp, style = MaterialTheme.typography.titleLarge)

        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TypeToggleButton("Expense", selectedType == "expense") { selectedType = "expense" }
            TypeToggleButton("Income", selectedType == "income") { selectedType = "income" }
        }

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            leadingIcon = { Text("$") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            placeholder = { Text("What was this for?") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(
                readOnly = true,
                value = selectedCategory,
                onValueChange = {},
                label = { Text("Category") },
                trailingIcon = {
                    Icon(Icons.Filled.ExpandMore, contentDescription = "Expand")
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category) },
                        onClick = {
                            selectedCategory = category
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = {
                val parsedAmount = amount.toDoubleOrNull() ?: 0.0
                if (parsedAmount > 0.0) {
                    coroutineScope.launch {
                        viewModel.addTransaction(
                            Transaction(
                                amount = parsedAmount,
                                type = selectedType,
                                description = description,
                                category = selectedCategory
                            )
                        )
                        amount = ""
                        description = ""
                        selectedCategory = "Food"
                        selectedType = "expense"
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Save Transaction")
        }
    }
}

@Composable
fun TypeToggleButton(label: String, selected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary else Color.LightGray,
            contentColor = if (selected) Color.White else Color.Black
        )
    ) {
        Text(label)
    }
}
