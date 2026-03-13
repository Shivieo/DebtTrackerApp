package com.example.debttrackerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.room.Room
import com.example.debttrackerapp.total_debt_simple.App
import com.example.debttrackerapp.total_debt_simple.DebtDatabase
import com.example.debttrackerapp.total_debt_simple.DebtViewModel
import com.example.debttrackerapp.ui.theme.DebtTrackerAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1. Initialize the Database
        val db = Room.databaseBuilder(
            applicationContext,
            DebtDatabase::class.java,
            "debt_db"
        )
            .fallbackToDestructiveMigration()
            .build()

        // 2. Get the DAO
        val dao = db.dao

        val AdvDebtDao = db.AdvDebtDao

        // 3. Create the ViewModel (Manual way for now to keep it simple)
        val viewModel = DebtViewModel(dao, AdvDebtDao)
        enableEdgeToEdge()
        setContent {
            DebtTrackerAppTheme {
                App(viewModel = viewModel)
            }
        }
    }
}