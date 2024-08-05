package com.kisahcode.jetpackcomposelabs.ui.screen.cart

import com.kisahcode.jetpackcomposelabs.model.OrderReward

/**
 * Data class that represents the state of the cart screen.
 *
 * This class holds the list of order rewards and the total required points needed to redeem those rewards.
 *
 * @property orderReward List of [OrderReward] objects representing the items in the cart.
 * @property totalRequiredPoint Total points required to redeem all the items in the cart.
 */
data class CartState(
    val orderReward: List<OrderReward>,
    val totalRequiredPoint: Int
)