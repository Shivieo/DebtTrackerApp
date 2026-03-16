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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DebtTrackerAppTheme {
                App()
            }
        }
    }
}