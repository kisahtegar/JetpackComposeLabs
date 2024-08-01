package com.kisahcode.jetpackcomposelabs.model

/**
 * Data class representing a Hero.
 *
 * @param id Unique identifier for the hero.
 * @param name Name of the hero.
 * @param photoUrl URL to the hero's photo.
 */
data class Hero(
    val id: String,
    val name: String,
    val photoUrl: String
)