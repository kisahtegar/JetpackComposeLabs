package com.kisahcode.jetpackcomposelabs.model

import com.kisahcode.jetpackcomposelabs.R

/**
 * Data class representing a menu item with an image, title, and price.
 *
 * @property image The resource ID of the menu item image.
 * @property title The title of the menu item.
 * @property price The price of the menu item.
 */
data class Menu(
    val image: Int,
    val title: String,
    val price: String,
)

/**
 * A list of dummy menu items with predefined images, titles, and prices.
 */
val dummyMenu = listOf(
    Menu(R.drawable.menu1, "Tiramisu Coffee Milk", "Rp 28.000"),
    Menu(R.drawable.menu2, "Pumpkin Spice Latte", "Rp 18.000"),
    Menu(R.drawable.menu3, "Light Cappuccino", "Rp 20.000"),
    Menu(R.drawable.menu4, "Choco Creamy Latte", "Rp 16.000"),
)

/**
 * A shuffled list of dummy best-seller menu items.
 */
val dummyBestSellerMenu = dummyMenu.shuffled()
