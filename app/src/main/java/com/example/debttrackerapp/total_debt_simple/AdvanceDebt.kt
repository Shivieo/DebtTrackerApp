package com.example.debttrackerapp.total_debt_simple

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvanceDebtTracker(
    navController: NavController,
    viewModel: DebtViewModel
) {

    val debtList by viewModel.advDebts.collectAsState(initial = emptyList())

    // Get the amount from the most recent entry, or 0 if the list is empty
    val recentBusinessDebt = debtList.firstOrNull()?.amount ?: 0

    // States for dropdowns
    var expanded by remember { mutableStateOf(false) }
    var expandedSec by remember { mutableStateOf(false) }
    var expandedThi by remember { mutableStateOf(false) }
    val optionsInc = listOf(
        "Credit Purchase",
        "Assets purchase on credit",
        "Outstanding Salary",
        "Outstanding Rent",
        "Miscellaneous outstandings"
    )
    val optionsDec = listOf("Sales", "Miscellaneous sales")
    val optionsExp = listOf("Rent", "Electricity", "Water", "Miscellaneous expenses")
    var selectedOptionInc by remember { mutableStateOf(optionsInc[0]) }
    var selectedOptionDec by remember { mutableStateOf(optionsDec[0]) }
    var selectedOptionsExp by remember { mutableStateOf(optionsExp[0]) }

    var amountInput by remember { mutableStateOf("") }
    var amountInput2 by remember { mutableStateOf("") }
    var amountInput3 by remember { mutableStateOf("") }
    var statusMessage by remember { mutableStateOf("") }
    var advDebtToDelete by remember {
        mutableStateOf<AdvanceDebtEntry?>(null)
    }

    advDebtToDelete?.let { debt ->
        AlertDialog(
            onDismissRequest = { advDebtToDelete = null }, // Hide if they click outside
            title = { Text("Confirm Delete") },
            text = { Text("Are you sure you want to delete '${debt.title}'?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.deleteAdvDebt(debt)
                        advDebtToDelete = null // Hide after deleting
                    }
                ) {
                    Text("Delete", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { advDebtToDelete = null }
                ) {
                    Text("Cancel")
                }
            }
        )
    }


    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        item {
            Text("Advanced Debt Entry", style = MaterialTheme.typography.headlineMedium)
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = androidx.compose.material3.CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Last Recorded Debt", style = MaterialTheme.typography.labelLarge)
                    Text(
                        text = "₹$recentBusinessDebt",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        item {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedOptionInc,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Category") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true)
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    optionsInc.forEach { optionsInc ->
                        DropdownMenuItem(
                            text = { Text(optionsInc) },
                            onClick = {
                                selectedOptionInc = optionsInc
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        item {
            OutlinedTextField(
                value = amountInput,
                onValueChange = { amountInput = it },
                label = { Text("Enter Amount") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
        }

        // second
        item {
            ExposedDropdownMenuBox(
                expanded = expandedSec,
                onExpandedChange = { expandedSec = !expandedSec }
            ) {
                OutlinedTextField(
                    value = selectedOptionDec,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Category") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedSec) },
                    modifier = Modifier
                        .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true)
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expandedSec,
                    onDismissRequest = { expandedSec = false }
                ) {
                    optionsDec.forEach { optionsDec ->
                        DropdownMenuItem(
                            text = { Text(optionsDec) },
                            onClick = {
                                selectedOptionDec = optionsDec
                                expandedSec = false
                            }
                        )
                    }
                }
            }
        }

        item {
            OutlinedTextField(
                value = amountInput2,
                onValueChange = { amountInput2 = it },
                label = { Text("Enter Amount") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
        }

        // third
        item {
            ExposedDropdownMenuBox(
                expanded = expandedThi,
                onExpandedChange = { expandedThi = !expandedThi }
            ) {
                OutlinedTextField(
                    value = selectedOptionsExp,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Category") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedThi) },
                    modifier = Modifier
                        .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true)
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expandedThi,
                    onDismissRequest = { expandedThi = false }
                ) {
                    optionsExp.forEach { optionsExp ->
                        DropdownMenuItem(
                            text = { Text(optionsExp) },
                            onClick = {
                                selectedOptionsExp = optionsExp
                                expandedThi = false
                            }
                        )
                    }
                }
            }
        }

        item {
            OutlinedTextField(
                value = amountInput3,
                onValueChange = { amountInput3 = it },
                label = { Text("Enter Amount") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            Button(
                onClick = {
                    val currentDebt = amountInput.toLongOrNull() ?: 0
                    val amountDec = amountInput2.toLongOrNull() ?: 0
                    val amountExp = amountInput3.toLongOrNull() ?: 0
                    val total = recentBusinessDebt + currentDebt - (amountDec - amountExp)

                    statusMessage = when {
                        total > recentBusinessDebt -> "Pay Attention!!!, Your debt is increased and becomes $total"
                        total < recentBusinessDebt -> "Your debt is decreasing and become $total"
                        else -> "Your debt remains as it is $total"
                    }

                    amountInput = total.toString()
                    viewModel.addAdvanceDebt("Updated Business Debt", total.toString())
                    amountInput = ""
                    amountInput2 = ""
                    amountInput3 = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Debt Condition")
            }

        }

        if (statusMessage.isNotEmpty()) {
            item {
                Text(text = statusMessage)
            }
        }

        // make clear button here
        item {
            Button(
                onClick = {
                    amountInput = ""
                    amountInput2 = ""
                    amountInput3 = ""
                    statusMessage = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Clear")
            }
        }


        item {
            Button(
                onClick = {
                    navController.navigate(Routes.Home)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back")
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Debts on business", style = MaterialTheme.typography.titleLarge)
        }

        items(debtList) { debt ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = debt.title)
                        Text(text = "₹${debt.amount}", fontWeight = FontWeight.Bold)
                    }
                    IconButton(
                        onClick = { advDebtToDelete = debt }
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