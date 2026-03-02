package com.example.debttrackerapp.total_debt_simple

import kotlinx.serialization.Serializable

sealed class Navigation {
    @Serializable
    object Main: Navigation()
}

sealed class Routes {
    @Serializable
    object Home: Routes()

    @Serializable
    object BasicDebt: Routes()

    @Serializable
    object AdvanceDebt: Routes()
}