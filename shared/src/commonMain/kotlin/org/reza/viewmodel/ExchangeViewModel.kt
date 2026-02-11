package org.reza.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.reza.data.model.response.ExchangeResponse
import org.reza.data.repository.ExchangeRepository

class ExchangeViewModel(
    private val repository: ExchangeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ExchangeUiState>(ExchangeUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun fetchRates(base: String) {
        viewModelScope.launch {
            _uiState.value = ExchangeUiState.Loading
            repository.getRates(base)
                .onSuccess { response ->
                    _uiState.value = ExchangeUiState.Success(response)
                }
                .onFailure { error ->
                    _uiState.value = ExchangeUiState.Error(error.message ?: "Unknown Error")
                }
        }
    }
}

/**
 * Represents the state of the exchange screen.
 */
sealed interface ExchangeUiState {
    /**
     * Represents the initial state of the exchange screen.
     */
    data object Idle : ExchangeUiState

    /**
     * Represents the loading state of the exchange screen.
     */
    data object Loading : ExchangeUiState

    /**
     * Represents the success state of the exchange screen.
     *
     * @property data The exchange response data.
     */
    data class Success(val data: ExchangeResponse) : ExchangeUiState

    /**
     * Represents the error state of the exchange screen.
     *
     * @property message The error message.
     */
    data class Error(val message: String) : ExchangeUiState
}