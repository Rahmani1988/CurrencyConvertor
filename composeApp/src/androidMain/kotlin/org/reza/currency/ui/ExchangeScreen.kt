package org.reza.currency.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import org.reza.currency.data.model.response.ExchangeResponse
import org.reza.currency.viewmodel.ExchangeUiState
import org.reza.currency.viewmodel.ExchangeViewModel

@Composable
fun ExchangeScreen(
    viewModel: ExchangeViewModel = koinViewModel(),
    onNavigateToDetails: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = { viewModel.fetchRates("USD") }) {
            Text("Fetch USD Rates")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (val state = uiState) {
            is ExchangeUiState.Idle -> Text("Enter a currency to start")
            is ExchangeUiState.Loading -> CircularProgressIndicator()
            is ExchangeUiState.Error -> Text("Error: ${state.message}", color = Color.Red)
            is ExchangeUiState.Success -> RateList(state.data)
        }
    }
}

@Composable
fun RateList(data: ExchangeResponse) {
    LazyColumn {
        items(data.conversionRates.toList()) { (currency, rate) ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = currency, fontWeight = FontWeight.Bold)
                Text(text = rate.toString())
            }
        }
    }
}