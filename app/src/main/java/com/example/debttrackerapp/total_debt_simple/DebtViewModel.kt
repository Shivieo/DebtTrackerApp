package com.example.debttrackerapp.total_debt_simple

import androidx.compose.foundation.layout.size
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.debttrackerapp.news.Article
import com.example.debttrackerapp.news.NewsApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DebtViewModel @Inject constructor(
    private val dao: DebtDao,
    private val AdvDebtDao: AdvanceDebtDao,
    private val newsApiService: NewsApiService
) : ViewModel() {
    // This "Flow" automatically updates the UI whenever the DB changes
    val debts = dao.getAllDebts()

    fun addDebt(title: String, amount: String) {
        val amountInt = amount.toIntOrNull() ?: 0
        val newEntry = DebtEntry(basicTitle = title, basicAmount = amountInt)

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

    private val _newsArticles = MutableStateFlow<List<Article>>(emptyList())
    val newsArticles: StateFlow<List<Article>> = _newsArticles

    // Fetch news articles from the API
    fun fetchStockNews() {
        viewModelScope.launch {
            android.util.Log.d("NEWS_DEBUG", "Starting API Call...")
            try {
                val response = newsApiService.getStockNews(apiKey = "c8ee0b5232a749d4862c383e4341670a")

                android.util.Log.d("NEWS_DEBUG", "Server Response Status: ${response.status}")

                if (response.status == "ok") {
                    val list = response.articles ?: emptyList()
                    android.util.Log.d("NEWS_DEBUG", "Success! Received ${list.size} articles")
                    _newsArticles.value = list
                } else {
                    // This will tell us if it's an API Key issue or something else
                    android.util.Log.e("NEWS_DEBUG", "API Error: ${response.code} - ${response.message}")
                }
            } catch (e: Exception) {
                android.util.Log.e("NEWS_DEBUG", "Connection Failed: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    // Auto fetch news when the viewModel is created
    init {
        fetchStockNews()
    }
}