package com.example.financialmanager.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialmanager.data.db.AppDatabase
import com.example.financialmanager.data.model.Budget
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class BudgetViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val dao = db.budgetDao()

    val budgets = dao.getAll().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        emptyList()
    )

    fun addBudget(budget: Budget) = viewModelScope.launch {
        dao.insert(budget)
    }

    fun clearAllBudgets() = viewModelScope.launch {
        dao.clearAll()
    }

}
