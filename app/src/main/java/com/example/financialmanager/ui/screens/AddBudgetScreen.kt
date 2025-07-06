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
import com.example.financialmanager.data.model.Budget
import com.example.financialmanager.viewmodel.BudgetViewModel
import kotlinx.coroutines.launch

@Composable
fun AddBudgetScreen(viewModel: BudgetViewModel) {
    var selectedPeriod by remember { mutableStateOf("Monthly") }
    var selectedCategory by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var categoryDropdownExpanded by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val categories = listOf("Food", "Transport", "Bills", "Shopping", "Health", "Entertainment")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "budgets/add", fontSize = 22.sp, style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Budget Period", style = MaterialTheme.typography.labelMedium)
        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BudgetToggleButton(
                label = "Monthly",
                selected = selectedPeriod == "Monthly",
                onClick = { selectedPeriod = "Monthly" },
                modifier = Modifier.weight(1f)
            )
            BudgetToggleButton(
                label = "Weekly",
                selected = selectedPeriod == "Weekly",
                onClick = { selectedPeriod = "Weekly" },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Category", style = MaterialTheme.typography.labelMedium)
        ExposedDropdownMenuBox(
            expanded = categoryDropdownExpanded,
            onExpandedChange = { categoryDropdownExpanded = !categoryDropdownExpanded }
        ) {
            OutlinedTextField(
                value = selectedCategory,
                onValueChange = {},
                readOnly = true,
                label = { Text("Select a category") },
                trailingIcon = { Icon(Icons.Default.ExpandMore, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = categoryDropdownExpanded,
                onDismissRequest = { categoryDropdownExpanded = false }
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category) },
                        onClick = {
                            selectedCategory = category
                            categoryDropdownExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Budget Amount", style = MaterialTheme.typography.labelMedium)
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("$ 0.00") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                val parsedAmount = amount.toDoubleOrNull()
                if (parsedAmount != null && selectedCategory.isNotBlank()) {
                    coroutineScope.launch {
                        viewModel.addBudget(
                            Budget(
                                period = selectedPeriod,
                                category = selectedCategory,
                                amount = parsedAmount
                            )
                        )
                        selectedPeriod = "Monthly"
                        selectedCategory = ""
                        amount = ""
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            enabled = amount.toDoubleOrNull() != null && selectedCategory.isNotBlank()
        ) {
            Text("Create Budget")
        }
    }
}

@Composable
fun BudgetToggleButton(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary else Color.LightGray,
            contentColor = if (selected) Color.White else Color.Black
        ),
        modifier = modifier
            .height(40.dp)
    ) {
        Text(label)
    }
}
