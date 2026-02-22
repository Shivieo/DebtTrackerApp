package com.example.debttrackerapp.total_debt_simple

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "debts")
data class DebtEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val amount: Int
)