package com.kisahcode.jetpackcomposelabs.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kisahcode.jetpackcomposelabs.data.RewardRepository
import com.kisahcode.jetpackcomposelabs.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the Cart screen.
 *
 * This ViewModel manages the business logic for the cart screen, including fetching and updating
 * order rewards from the repository and updating the UI state accordingly.
 *
 * @property repository The repository used to interact with reward data.
 */

class CartViewModel(
    private val repository: RewardRepository
) : ViewModel() {

    // State flow to manage the UI state of the cart screen.
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>> get() = _uiState

    /**
     * Fetches the list of added order rewards and updates the UI state.
     *
     * This function retrieves the list of order rewards that have a non-zero count
     * from the repository and calculates the total required points for these rewards.
     * The UI state is updated with the fetched data, transitioning from a loading state
     * to a success state.
     */
    fun getAddedOrderRewards() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderRewards()
                .collect { orderReward ->
                    val totalRequiredPoint =
                        orderReward.sumOf { it.reward.requiredPoint * it.count }
                    _uiState.value = UiState.Success(CartState(orderReward, totalRequiredPoint))
                }
        }
    }

    /**
     * Updates the order reward count for a specific reward and refreshes the cart state.
     *
     * This function updates the count of a specific reward in the order list and then refreshes
     * the cart state to reflect the changes. If the update is successful, the added order rewards
     * are re-fetched to update the UI state.
     *
     * @param rewardId The ID of the reward to update.
     * @param count The new count value for the reward.
     */
    fun updateOrderReward(rewardId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderReward(rewardId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderRewards()
                    }
                }
        }
    }
}