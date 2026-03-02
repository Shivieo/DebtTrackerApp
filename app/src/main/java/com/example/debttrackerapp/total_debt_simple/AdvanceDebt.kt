package com.example.debttrackerapp.total_debt_simple

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AdvanceDebtTracker(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.Cyan),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Hello this is for testing for Advance Debt Tracker")

        Spacer(Modifier.height(25.dp))

        Button(
            onClick = {
                navController.navigate(Routes.Home)
            }
        ) {
            Text("Back")
        }
    }
}