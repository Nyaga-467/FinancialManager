package com.example.financialmanager.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val amount: Double,
    val type: String, // "income" or "expense"
    val description: String,
    val category: String,
    val date: Long = System.currentTimeMillis()
)
