package com.kisahcode.jetpackcomposelabs.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kisahcode.jetpackcomposelabs.data.RewardRepository
import com.kisahcode.jetpackcomposelabs.model.OrderReward
import com.kisahcode.jetpackcomposelabs.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * ViewModel for managing and providing the UI state for the home screen.
 *
 * This ViewModel fetches and manages a list of [OrderReward] items from the [RewardRepository].
 * It provides the current UI state through the [uiState] flow, which can be either [UiState.Loading],
 * [UiState.Success] with the list of rewards, or [UiState.Error] if an error occurs.
 *
 * @property repository The repository used to retrieve the rewards data.
 */
class HomeViewModel(
    private val repository: RewardRepository
) : ViewModel() {

    // Private mutable state flow for holding the UI state of the home screen.
    private val _uiState: MutableStateFlow<UiState<List<OrderReward>>> = MutableStateFlow(UiState.Loading)
    // Public state flow exposed to the UI for observing the current UI state.
    val uiState: StateFlow<UiState<List<OrderReward>>> get() = _uiState

    /**
     * Retrieves all rewards from the repository and updates the UI state.
     *
     * This function initiates a data retrieval operation from the [RewardRepository]. It sets the
     * UI state to [UiState.Loading] while the data is being fetched. Upon successful retrieval,
     * the UI state is updated with [UiState.Success] containing the list of [OrderReward]. If an
     * error occurs, the UI state is updated with [UiState.Error] containing the error message.
     */
    fun getAllRewards() {
        viewModelScope.launch {
            repository.getAllRewards()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }
}