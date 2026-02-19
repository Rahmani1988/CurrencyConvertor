package org.reza.currency.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.reza.currency.ui.DetailsScreen
import org.reza.currency.ui.ExchangeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Exchange.route) {
        composable(Screen.Exchange.route) {
            ExchangeScreen(
                onNavigateToDetails = { currencyCode ->
                    navController.navigate("${Screen.Details.route}/$currencyCode")
                }
            )
        }

        composable("${Screen.Details.route}/{currency}") { backStackEntry ->
            val currency = backStackEntry.arguments?.getString("currency") ?: ""
            DetailsScreen(currency = currency, onBack = { navController.popBackStack() })
        }
    }
}