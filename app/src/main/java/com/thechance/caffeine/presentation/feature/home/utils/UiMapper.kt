package com.thechance.caffeine.presentation.feature.home.utils

import androidx.annotation.DrawableRes
import com.thechance.caffeine.R

@DrawableRes
fun getImageResourceFromCoffeeName(coffeeName: String): Int {
    return when (coffeeName) {
        "Black" -> R.drawable.black_empty_cup
        "Espresso" -> R.drawable.espresso_empty_cup
        "Latte" -> R.drawable.latte_empty_cup
        "Macchiato" -> R.drawable.macchiato_empty_cup
        else -> throw Exception("Invalid coffee name")
    }
}