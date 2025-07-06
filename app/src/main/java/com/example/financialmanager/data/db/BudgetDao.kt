package com.example.financialmanager.data.db


import androidx.room.*
import com.example.financialmanager.data.model.Budget
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(budget: Budget)

    @Query("SELECT * FROM Budget")
    fun getAll(): Flow<List<Budget>>

    @Query("DELETE FROM Budget")
    suspend fun clearAll()

}
