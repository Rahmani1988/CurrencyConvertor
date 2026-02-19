package org.reza.currency.navigation

sealed class Screen(val route: String) {
    data object Exchange : Screen("exchange")
    data object Details : Screen("details/{currencyCode}") {
        fun createRoute(currencyCode: String) = "details/$currencyCode"
    }
}