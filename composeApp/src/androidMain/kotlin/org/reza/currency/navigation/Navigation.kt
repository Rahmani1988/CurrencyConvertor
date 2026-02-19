package org.reza.currency.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.reza.currency.ui.ExchangeScreen
import org.reza.viewmodel.ExchangeViewModel

// todo working on this
@Composable
fun RootNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "exchange") {
        composable("exchange") {
            // This retrieves the ViewModel from Koin.
            // It is scoped to this specific navigation destination.
            //val viewModel: ExchangeViewModel = koinViewModel()

            //ExchangeScreen(viewModel)
        }
    }
}