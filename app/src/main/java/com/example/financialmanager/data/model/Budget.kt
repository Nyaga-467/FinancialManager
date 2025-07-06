package com.example.financialmanager.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Budget(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val period: String, // "Monthly" or "Weekly"
    val category: String,
    val amount: Double
)
