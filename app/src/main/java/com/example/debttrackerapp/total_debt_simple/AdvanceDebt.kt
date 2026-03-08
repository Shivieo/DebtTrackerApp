package com.example.debttrackerapp.total_debt_simple

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvanceDebtTracker(navController: NavController) {

    // States for dropdowns
    var expanded by remember { mutableStateOf(false) }
    var expandedSec by remember { mutableStateOf(false) }
    var expandedThi by remember { mutableStateOf(false) }
    val optionsInc = listOf("Credit Purchase", "Assets purchase on credit", "Outstanding Salary", "Outstanding Rent", "Miscellaneous outstandings")
    val optionsDec = listOf("Sales", "Miscellaneous sales")
    val optionsExp = listOf("Rent", "Electricity", "Water", "Miscellaneous expenses")
    var selectedOptionInc by remember { mutableStateOf(optionsInc[0]) }
    var selectedOptionDec by remember { mutableStateOf(optionsDec[0]) }
    var selectedOptionsExp by remember { mutableStateOf(optionsExp[0]) }

    var amountInput by remember { mutableStateOf("") }
    var amountInput2 by remember { mutableStateOf("") }
    var amountInput3 by remember { mutableStateOf("") }
    var statusMessage by remember { mutableStateOf("") }


    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Advanced Debt Entry", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(10.dp))

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

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = amountInput,
            onValueChange = { amountInput = it },
            label = { Text("Enter Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        // second
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
                            selectedOptionInc = optionsDec
                            expandedSec = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = amountInput2,
            onValueChange = { amountInput2 = it },
            label = { Text("Enter Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        // third
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

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = amountInput3,
            onValueChange = { amountInput3 = it },
            label = { Text("Enter Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))

        Button(
            onClick = {
                val currentDebt = amountInput.toIntOrNull() ?: 0
                val amountDec = amountInput2.toIntOrNull() ?: 0
                val amountExp = amountInput3.toIntOrNull() ?: 0
                val total = currentDebt - (amountDec - amountExp)

                statusMessage = when {
                    total > currentDebt -> "Pay Attention!!!, Your debt is increased and becomes $total"
                    total < currentDebt -> "Your debt is decreasing and become $total"
                    else -> "Your debt remains as it is $total"
                }

                amountInput = total.toString()
            }
        ) {
            Text("Debt Condition")
        }

        if (statusMessage.isNotEmpty()) {
            Text(text = statusMessage)
        }

        // make clear button here
        Button(
            onClick = {
                amountInput = ""
                amountInput2 = ""
                amountInput3 = ""
                statusMessage = ""
            }
        ) {
            Text("Clear")
        }

        Button(
            onClick = {
                navController.navigate(Routes.Home)
            }
        ) {
            Text("Back")
        }
    }
}