package com.example.debttrackerapp.total_debt_simple

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DebtDao {
    @Upsert // This handles both insert and update!
    suspend fun upsertDebt(debt: DebtEntry)

    @Delete
    suspend fun deleteDebt(debt: DebtEntry)

    @Query("SELECT * FROM debts ORDER BY basicId DESC")
    fun getAllDebts(): Flow<List<DebtEntry>>
}

@Dao
interface AdvanceDebtDao {
    @Upsert // This handles both insert and update!
    suspend fun upsertAdvanceDebt(debt: AdvanceDebtEntry)

    @Delete
    suspend fun deleteAdvanceDebt(debt: AdvanceDebtEntry)

    @Query("SELECT * FROM advanceDebts ORDER BY id DESC")
    fun getAllAdvanceDebts(): Flow<List<AdvanceDebtEntry>>
}