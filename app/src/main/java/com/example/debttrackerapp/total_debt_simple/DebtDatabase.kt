package com.example.debttrackerapp.total_debt_simple

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DebtEntry::class], version = 1)
abstract class DebtDatabase : RoomDatabase() {
    abstract val dao: DebtDao
}