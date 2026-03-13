package com.example.debttrackerapp.total_debt_simple

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "debts")
data class DebtEntry(
    @PrimaryKey(autoGenerate = true)
    val basicId: Int = 0,
    val basicTitle: String,
    val basicAmount: Int
)

@Entity(tableName = "advanceDebts")
data class AdvanceDebtEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val amount: Int

)