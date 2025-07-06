package com.example.financialmanager.data.db


import androidx.room.*
import com.example.financialmanager.data.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: Transaction)

    @Query("SELECT * FROM `Transaction` ORDER BY date DESC")
    fun getAll(): Flow<List<Transaction>>

    @Query("DELETE FROM `Transaction`")
    suspend fun clearAll()

}
