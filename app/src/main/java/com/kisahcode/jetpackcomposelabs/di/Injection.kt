package com.kisahcode.jetpackcomposelabs.di

import com.kisahcode.jetpackcomposelabs.data.RewardRepository

/**
 * Object that provides dependency injection for the application.
 *
 * This object is responsible for providing instances of classes needed throughout the application.
 * It helps in managing dependencies and ensuring that the necessary instances are available when
 * needed.
 */
object Injection {

    /**
     * Provides an instance of [RewardRepository].
     *
     * This method returns a singleton instance of [RewardRepository] by calling its `getInstance`
     * method. The singleton pattern ensures that the same instance of [RewardRepository] is used
     * throughout the application, which helps in maintaining a consistent state and reducing
     * resource consumption.
     *
     * @return A singleton instance of [RewardRepository].
     */
    fun provideRepository(): RewardRepository {
        return RewardRepository.getInstance()
    }
}