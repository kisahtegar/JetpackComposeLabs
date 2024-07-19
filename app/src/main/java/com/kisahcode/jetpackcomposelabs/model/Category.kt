package com.kisahcode.jetpackcomposelabs.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.kisahcode.jetpackcomposelabs.R

/**
 * Data class representing a coffee category with an image and text.
 *
 * @property imageCategory The resource ID of the category image.
 * @property textCategory The resource ID of the category text.
 */
data class Category(
    @DrawableRes val imageCategory: Int,
    @StringRes val textCategory: Int
)

/**
 * A list of dummy coffee categories with predefined images and text resources.
 */
val dummyCategory = listOf(
    R.drawable.icon_category_all to R.string.category_all,
    R.drawable.icon_category_americano to R.string.category_americano,
    R.drawable.icon_category_cappuccino to R.string.category_cappuccino,
    R.drawable.icon_category_espresso to R.string.category_espresso,
    R.drawable.icon_category_frappe to R.string.category_frappe,
    R.drawable.icon_category_latte to R.string.category_latte,
    R.drawable.icon_category_macchiato to R.string.category_macchiato,
    R.drawable.icon_category_mocha to R.string.category_mocha,
).map { Category(it.first, it.second) }
