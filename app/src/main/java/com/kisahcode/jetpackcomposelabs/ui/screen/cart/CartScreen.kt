package com.kisahcode.jetpackcomposelabs.ui.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kisahcode.jetpackcomposelabs.R
import com.kisahcode.jetpackcomposelabs.di.Injection
import com.kisahcode.jetpackcomposelabs.ui.ViewModelFactory
import com.kisahcode.jetpackcomposelabs.ui.common.UiState
import com.kisahcode.jetpackcomposelabs.ui.components.CartItem
import com.kisahcode.jetpackcomposelabs.ui.components.OrderButton

/**
 * Composable function that displays the cart screen.
 *
 * This function represents the UI for the cart screen, managing the state and displaying
 * the cart content based on the current UI state. It handles loading, success, and error states.
 *
 * @param modifier Modifier to be applied to the layout. Defaults to [Modifier].
 * @param viewModel The [CartViewModel] used to manage the state of the cart screen. Defaults to a
 * view model provided by [ViewModelFactory] with a repository from [Injection.provideRepository].
 */
@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    // Collect the UI state from the view model
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        // Handle different UI states
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAddedOrderRewards()
            }
            is UiState.Success -> {
                CartContent(
                    uiState.data,
                    onProductCountChanged = { rewardId, count ->
                        // Callback to update the count of a reward item
                        viewModel.updateOrderReward(rewardId, count)
                    },
                )
            }
            is UiState.Error -> {}
        }
    }
}

/**
 * Composable function that displays the content of the cart screen.
 *
 * This function represents the UI for the cart screen, including the app bar, list of cart items,
 * and order button. It uses a column layout to arrange these elements vertically and ensures the
 * cart's content fills the available space.
 *
 * @param state The current state of the cart, containing the list of ordered rewards and the total
 * required points.
 * @param onProductCountChanged Callback function to handle changes in the product count.
 * @param modifier Modifier to be applied to the layout. Defaults to [Modifier].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartContent(
    state: CartState,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    // String resource for the share message
    val shareMessage = stringResource(
        R.string.share_message,
        state.orderReward.count(), // Number of items in the cart
        state.totalRequiredPoint, // Total required points for the items in the cart
    )

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Center-aligned top app bar with a title
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.menu_cart),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        )
        // LazyColumn to display the list of cart items
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(weight = 1f)
        ) {
            items(state.orderReward, key = { it.reward.id }) { item ->
                // Display each cart item with details
                CartItem(
                    rewardId = item.reward.id,
                    image = item.reward.image,
                    title = item.reward.title,
                    totalPoint = item.reward.requiredPoint * item.count,
                    count = item.count,
                    onProductCountChanged = onProductCountChanged,
                )
                HorizontalDivider()
            }
        }
        // Button to place the order with the total points
        OrderButton(
            text = stringResource(R.string.total_order, state.totalRequiredPoint),
            enabled = state.orderReward.isNotEmpty(),
            onClick = {
            },
            modifier = Modifier.padding(16.dp)
        )
    }
}