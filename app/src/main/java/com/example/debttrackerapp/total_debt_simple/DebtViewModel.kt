package com.example.debttrackerapp.total_debt_simple

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DebtViewModel(private val dao: DebtDao) : ViewModel() {
    // This "Flow" automatically updates the UI whenever the DB changes
    val debts = dao.getAllDebts()

    fun addDebt(title: String, amount: String) {
        val amountInt = amount.toIntOrNull() ?: 0
        val newEntry = DebtEntry(title = title, amount = amountInt)

        // Database operations MUST happen in a Coroutine (background thread)
        viewModelScope.launch {
            dao.upsertDebt(newEntry)
        }
    }

    fun deleteDebt(debt: DebtEntry) {
        viewModelScope.launch {
            dao.deleteDebt(debt)
        }
    }

}