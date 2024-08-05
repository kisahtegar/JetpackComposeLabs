package com.kisahcode.jetpackcomposelabs.model

/**
 * Data class representing an order of a reward.
 *
 * This class models the details of an order where a user selects a reward and specifies
 * the quantity they wish to claim. It includes the reward itself and the count of how many
 * of that reward are ordered.
 *
 * @property reward The [Reward] object associated with this order. This represents the reward
 *                  that the user is claiming.
 * @property count The number of units of the [Reward] that the user wants to claim in this order.
 *                 This allows the application to handle multiple claims of the same reward.
 */
data class OrderReward(
    val reward: Reward,
    val count: Int
)