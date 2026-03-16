package com.example.debttrackerapp.total_debt_simple

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DebtViewModel @Inject constructor(private val dao: DebtDao, private val AdvDebtDao: AdvanceDebtDao) : ViewModel() {
    // This "Flow" automatically updates the UI whenever the DB changes
    val debts = dao.getAllDebts()

    fun addDebt(title: String, amount: String) {
        val amountInt = amount.toIntOrNull() ?: 0
        val newEntry = DebtEntry(basicTitle = title,basicAmount = amountInt)

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

    val advDebts = AdvDebtDao.getAllAdvanceDebts()

    fun addAdvanceDebt(title: String, amount: String) {
        val amountInt = amount.toIntOrNull() ?: 0
        val newEntry = AdvanceDebtEntry(title = title, amount = amountInt)

        viewModelScope.launch {
            AdvDebtDao.upsertAdvanceDebt(newEntry)
        }
    }

    fun deleteAdvDebt(debt: AdvanceDebtEntry) {
        viewModelScope.launch {
            AdvDebtDao.deleteAdvanceDebt(debt)
        }
    }

}