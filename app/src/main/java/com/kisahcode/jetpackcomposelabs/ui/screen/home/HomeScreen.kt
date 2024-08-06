package com.kisahcode.jetpackcomposelabs.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kisahcode.jetpackcomposelabs.R
import com.kisahcode.jetpackcomposelabs.di.Injection
import com.kisahcode.jetpackcomposelabs.model.OrderReward
import com.kisahcode.jetpackcomposelabs.ui.ViewModelFactory
import com.kisahcode.jetpackcomposelabs.ui.common.UiState
import com.kisahcode.jetpackcomposelabs.ui.components.RewardItem

/**
 * Composable function that displays the home screen.
 *
 * This function creates a full-screen layout and observes the UI state from [HomeViewModel].
 * Based on the state, it displays either a loading state, the content with rewards, or an
 * error state.
 *
 * @param modifier Optional [Modifier] to be applied to the layout.
 * @param viewModel The [HomeViewModel] instance responsible for providing the UI state.
 * @param navigationToDetail Callback to navigate to the detail screen when a reward item is clicked.
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigationToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                // Trigger loading of all rewards
                viewModel.getAllRewards()
            }
            is UiState.Success -> {
                HomeContent(
                    orderReward = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigationToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}

/**
 * Composable function that displays the content of the home screen.
 *
 * This function creates a LazyVerticalGrid layout displaying a list of reward items. Each reward
 * item is represented by [RewardItem] and is clickable to navigate to its detail screen.
 *
 * @param orderReward The list of [OrderReward] objects to display.
 * @param modifier Optional [Modifier] to be applied to the LazyVerticalGrid layout.
 *                 This allows customization of the layout's appearance and behavior.
 * @param navigateToDetail Callback to navigate to the detail screen when a reward item is clicked.
 */
@Composable
fun HomeContent(
    orderReward: List<OrderReward>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,

    ) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(orderReward) { data ->
            RewardItem(
                image = data.reward.image,
                title = data.reward.title,
                requiredPoint = data.reward.requiredPoint,
                modifier = Modifier.clickable {
                    // Navigate to detail screen on click
                    navigateToDetail(data.reward.id)
                }
            )
        }
    }
}