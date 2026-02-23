package com.example.debttrackerapp.total_debt_simple

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TotalDebt(viewModel: DebtViewModel) {

    val debtList by viewModel.debts.collectAsState(initial = emptyList())

    var currentLiabilities by remember {
        mutableStateOf("")
    }

    var longTermLiabilities by remember {
        mutableStateOf("")
    }

    var totalDebt by remember {
        mutableStateOf("0")
    }

    var debtToDelete by remember {
        mutableStateOf<DebtEntry?>(null)
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        debtToDelete?.let { debt->
            AlertDialog(
                onDismissRequest = { debtToDelete = null }, // Hide if they click outside
                title = { Text("Confirm Delete") },
                text = { Text("Are you sure you want to delete '${debt.title}'?") },
                confirmButton = {
                    TextButton(
                        onClick = {
                            viewModel.deleteDebt(debt)
                            debtToDelete = null // Hide after deleting
                        }
                    ) {
                        Text("Delete", color = MaterialTheme.colorScheme.error)
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { debtToDelete = null }
                    ) {
                        Text("Cancel")
                    }
                }

            )
        }

        TextField(
            value = currentLiabilities,
            onValueChange = { currentLiabilities = it },
            label = { Text("Current Liabilities") }
        )

        TextField(
            value = longTermLiabilities,
            onValueChange = { longTermLiabilities = it },
            label = { Text("Long Term Liabilities") }
        )

        Button(
            onClick = {
                val current = currentLiabilities.toIntOrNull() ?: 0
                val longTerm = longTermLiabilities.toIntOrNull() ?: 0
                val sum = current + longTerm
                totalDebt = sum.toString()
                viewModel.addDebt("Total Debt Entry", sum.toString())
            }
        ) {
            Text("Calculate & Save")
        }

        Text(text = "Total: $totalDebt")


        Button(
            onClick = {
                currentLiabilities = ""
                longTermLiabilities = ""
                totalDebt = "0"
            }
        ) {
            Text("Clear")
        }

        Text(text = "Recent Calculations", style = MaterialTheme.typography.titleLarge)

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(debtList) { debt ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column() {
                            Text(text = debt.title)
                            Text(text = "₹${debt.amount}", fontWeight = FontWeight.Bold)
                        }
                        IconButton(
                            onClick = { debtToDelete = debt }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Debt",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun TotalDebtPreview() {
//    TotalDebt(viewModel = null)
//}