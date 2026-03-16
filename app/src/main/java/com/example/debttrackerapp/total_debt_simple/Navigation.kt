package com.example.debttrackerapp.total_debt_simple

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Navigation.Main) {
        navigation<Navigation.Main>(startDestination = Routes.Home) {
            composable<Routes.Home> {
                HomeScreenDebt(navController = navController)
            }

            composable<Routes.BasicDebt> {
                val viewModel: DebtViewModel = hiltViewModel()
                TotalDebt(navController = navController, viewModel = viewModel)
            }

            composable<Routes.AdvanceDebt> {
                val viewModel: DebtViewModel = hiltViewModel()
                AdvanceDebtTracker(navController = navController, viewModel = viewModel)
            }
        }
    }
}