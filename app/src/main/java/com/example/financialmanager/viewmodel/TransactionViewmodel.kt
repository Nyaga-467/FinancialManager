package com.example.financialmanager.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialmanager.data.db.AppDatabase
import com.example.financialmanager.data.model.Transaction
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class TransactionViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val dao = db.transactionDao()

    val transactions = dao.getAll().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        emptyList()
    )

    fun addTransaction(transaction: Transaction) = viewModelScope.launch {
        dao.insert(transaction)
    }

    fun clearAllTransactions() = viewModelScope.launch {
        dao.clearAll()
    }

}
