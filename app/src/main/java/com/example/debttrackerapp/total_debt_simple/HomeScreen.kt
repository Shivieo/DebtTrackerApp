package com.example.debttrackerapp.total_debt_simple

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreenDebt(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                // Navigation to basic debt tracker
                navController.navigate(Routes.BasicDebt)
            }
        ) {
            Text("Basic Debt Tracker")
        }

        Spacer(Modifier.height(25.dp))

        Button(
            onClick = {
                // Navigation to Advance debt tracker
                navController.navigate(Routes.AdvanceDebt)
            }
        ) {
            Text("Advance Debt Tracker")
        }
    }
}