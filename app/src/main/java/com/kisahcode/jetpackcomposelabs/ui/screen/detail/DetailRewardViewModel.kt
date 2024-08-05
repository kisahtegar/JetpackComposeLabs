package com.kisahcode.jetpackcomposelabs.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kisahcode.jetpackcomposelabs.data.RewardRepository
import com.kisahcode.jetpackcomposelabs.model.OrderReward
import com.kisahcode.jetpackcomposelabs.model.Reward
import com.kisahcode.jetpackcomposelabs.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the details of a specific reward in the JetReward app.
 *
 * This ViewModel is responsible for handling the data and operations related to a single
 * reward item. It retrieves reward details from the repository and updates the cart when
 * a reward is added. The ViewModel maintains the UI state for the reward details.
 *
 * @param repository The [RewardRepository] used to fetch and update reward data.
 */
class DetailRewardViewModel(
    private val repository: RewardRepository
) : ViewModel() {

    // Internal mutable state flow holding the UI state for the reward detail.
    private val _uiState: MutableStateFlow<UiState<OrderReward>> = MutableStateFlow(UiState.Loading)
    // Public state flow exposing the UI state for the reward detail.
    val uiState: StateFlow<UiState<OrderReward>> get() = _uiState

    /**
     * Fetches the details of a reward by its ID and updates the UI state.
     *
     * This function sets the UI state to loading while retrieving the reward details from the
     * repository. Upon success, it updates the UI state with the retrieved [OrderReward] object.
     *
     * @param rewardId The ID of the reward to fetch.
     */
    fun getRewardById(rewardId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderRewardById(rewardId))
        }
    }

    /**
     * Adds a specified amount of a reward to the cart.
     *
     * This function updates the quantity of a reward in the cart. The updated quantity
     * is sent to the repository, which handles the update of the order reward.
     *
     * @param reward The [Reward] object to be added to the cart.
     * @param count The quantity of the reward to be added.
     */
    fun addToCart(reward: Reward, count: Int) {
        viewModelScope.launch {
            repository.updateOrderReward(reward.id, count)
        }
    }
}