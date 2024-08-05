package com.kisahcode.jetpackcomposelabs.model

/**
 * Data class representing a reward in the JetReward application.
 *
 * This class models the information required to represent a reward that users can exchange
 * their points for. Each reward includes an image, title, and the number of points needed to
 * claim it.
 *
 * @property id Unique identifier for the reward. This ID helps distinguish between different
 *              rewards in the application.
 * @property image Resource ID for the reward's image. This is used to display the reward's
 *                 visual representation in the UI.
 * @property title The name or description of the reward. This is displayed to the user to
 *                 indicate what they will receive upon claiming the reward.
 * @property requiredPoint The number of points required to claim the reward. This value
 *                         determines whether a user has enough points to exchange for the
 *                         reward.
 */
data class Reward(
    val id: Long,
    val image: Int,
    val title: String,
    val requiredPoint: Int,
)