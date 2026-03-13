package com.example.debttrackerapp.total_debt_simple

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController

@Composable
fun App(viewModel: DebtViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Navigation.Main) {
        navigation<Navigation.Main>(startDestination = Routes.Home) {
            composable<Routes.Home> {
                HomeScreenDebt(navController = navController)
            }

            composable<Routes.BasicDebt> {
                TotalDebt(navController = navController, viewModel = viewModel)
            }

            composable<Routes.AdvanceDebt> {
                AdvanceDebtTracker(navController = navController, viewModel = viewModel)
            }
        }
    }
}