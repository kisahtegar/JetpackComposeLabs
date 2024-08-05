package com.kisahcode.jetpackcomposelabs.ui.common

/**
 * A sealed class representing the different states of the UI.
 *
 * This class is used to represent the loading, success, and error states that the UI can be in.
 * It helps in handling different scenarios in a unified way, making the UI logic more readable
 * and maintainable.
 *
 * @param T The type of data that will be held in the success state.
 */
sealed class UiState<out T: Any?> {

    /**
     * Represents the loading state of the UI.
     *
     * This state indicates that a background operation is in progress and the UI should show a
     * loading indicator.
     */
    data object Loading : UiState<Nothing>()

    /**
     * Represents the success state of the UI.
     *
     * This state indicates that a background operation has completed successfully, and the UI can
     * now display the data.
     *
     * @param T The type of data that will be held in the success state.
     * @property data The data to be displayed by the UI.
     */
    data class Success<out T: Any>(val data: T) : UiState<T>()

    /**
     * Represents the error state of the UI.
     *
     * This state indicates that a background operation has failed, and the UI should display an
     * error message.
     *
     * @property errorMessage The error message to be displayed by the UI.
     */
    data class Error(val errorMessage: String) : UiState<Nothing>()
}