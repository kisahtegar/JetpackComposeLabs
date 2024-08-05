package com.kisahcode.jetpackcomposelabs.data

import com.kisahcode.jetpackcomposelabs.model.FakeRewardDataSource
import com.kisahcode.jetpackcomposelabs.model.OrderReward
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

/**
 * Repository class for managing and accessing reward data in the JetReward application.
 *
 * This class provides methods to retrieve, update, and filter rewards. It uses a list of
 * `OrderReward` objects to simulate a database. The list is initialized with dummy data
 * from `FakeRewardDataSource`. The repository follows the singleton pattern to ensure
 * a single instance is used throughout the application.
 */
class RewardRepository {

    // A mutable list to hold order rewards. This simulates a local database or data source.
    private val orderRewards = mutableListOf<OrderReward>()

    // Initialization block to populate the list with dummy rewards if it's empty.
    init {
        if (orderRewards.isEmpty()) {
            FakeRewardDataSource.dummyRewards.forEach {
                orderRewards.add(OrderReward(it, 0))
            }
        }
    }

    /**
     * Retrieves the complete list of all order rewards.
     *
     * This function provides access to the entire list of `OrderReward` items. The list includes
     * rewards that may or may not have been added to an order (i.e., rewards with any count value,
     * including zero). The function returns this list as a `Flow` of the list, which supports
     * asynchronous operations and allows for real-time updates and observation in the UI or other
     * components.
     *
     * @return A `Flow` emitting a list of all `OrderReward` objects. This list includes all rewards
     *         with their associated count values, which may be zero if the reward has not been added
     *         to an order.
     */
    fun getAllRewards(): Flow<List<OrderReward>> {
        // Return the list of all rewards as a Flow
        return flowOf(orderRewards)
    }

    /**
     * Retrieves a specific order reward by its unique identifier.
     *
     * This function searches for and returns the `OrderReward` object corresponding to the given
     * reward ID. It is useful for fetching detailed information about a particular reward, including
     * its count in the current order. The function assumes that the provided reward ID exists in the
     * list; if the ID is not found, it will throw a `NoSuchElementException`.
     *
     * @param rewardId The unique identifier of the reward to be retrieved.
     * @return The `OrderReward` object associated with the provided reward ID. This includes the
     *         reward details and the current count of the reward in the order.
     * @throws NoSuchElementException If no `OrderReward` object with the specified reward ID is found.
     */
    fun getOrderRewardById(rewardId: Long): OrderReward {
        // Find and return the OrderReward with the specified ID
        return orderRewards.first {
            it.reward.id == rewardId
        }
    }

    /**
     * Updates the count of an existing order reward identified by its ID.
     *
     * This function searches for an `OrderReward` in the list by its ID and updates its count to
     * the specified new value. If the reward is found, the count is updated, and the function
     * returns a `Flow` emitting `true` to indicate success. If the reward with the given ID
     * is not found, the function returns a `Flow` emitting `false`.
     *
     * The update is performed synchronously on the in-memory list of rewards, and the result
     * is returned as a `Flow` which allows asynchronous handling in the UI or other components.
     *
     * @param rewardId The ID of the reward to update. This is used to locate the specific `OrderReward`
     *                 in the list.
     * @param newCountValue The new count value to set for the specified reward. This represents
     *                      the updated number of units for that reward.
     * @return A `Flow` emitting `true` if the reward was successfully updated, or `false` if
     *         no reward with the given ID was found. This allows observers to react to the result
     *         of the update operation asynchronously.
     */
    fun updateOrderReward(rewardId: Long, newCountValue: Int): Flow<Boolean> {
        // Find the index of the order reward with the specified ID
        val index = orderRewards.indexOfFirst { it.reward.id == rewardId }

        // Determine if the reward was found and update it
        val result = if (index >= 0) {
            // Retrieve the existing order reward
            val orderReward = orderRewards[index]
            // Update the order reward with the new count value
            orderRewards[index] = orderReward.copy(reward = orderReward.reward, count = newCountValue)
            // Indicate that the update was successful
            true
        } else {
            // Indicate that no reward with the specified ID was found
            false
        }

        // Return the result as a Flow
        return flowOf(result)
    }

    /**
     * Retrieves a list of order rewards that have a count greater than zero.
     *
     * This function filters the list of `OrderReward` items to include only those where the count
     * is not zero. It uses the `getAllRewards` function to obtain the complete list of rewards,
     * then applies a filter to exclude rewards with a count of zero. The filtered list is returned
     * as a `Flow` of the list, allowing for asynchronous handling and observation in the UI or
     * other components.
     *
     * @return A `Flow` emitting a list of `OrderReward` objects that have a count greater than zero.
     *         This list includes only the rewards that have been added to the cart or have been
     *         marked for an order.
     */
    fun getAddedOrderRewards(): Flow<List<OrderReward>> {
        // Get all rewards and filter out those with a count of zero
        return getAllRewards()
            .map { orderRewards ->
                // Filter rewards to include only those with a count greater than zero
                orderRewards.filter { orderReward ->
                    orderReward.count != 0
                }
            }
    }

    /**
     * Companion object for the `RewardRepository` class.
     *
     * This companion object provides a singleton instance of `RewardRepository`. It ensures that
     * there is only one instance of the repository throughout the application, which can be accessed
     * globally via the `getInstance()` method. This pattern is useful for managing shared data
     * and ensuring that the repository is only initialized once.
     */
    companion object {
        // Volatile variable to hold the single instance of RewardRepository
        @Volatile
        private var instance: RewardRepository? = null

        /**
         * Retrieves the singleton instance of `RewardRepository`.
         *
         * This method provides a global point of access to the `RewardRepository` instance. It uses
         * double-checked locking to ensure that the repository is instantiated only once, even in
         * multi-threaded environments. If the instance does not exist, it creates a new one and
         * assigns it to the `instance` variable.
         *
         * @return The singleton instance of `RewardRepository`.
         */
        fun getInstance(): RewardRepository =
            instance ?: synchronized(this) {
                RewardRepository().apply {
                    instance = this
                }
            }
    }
}