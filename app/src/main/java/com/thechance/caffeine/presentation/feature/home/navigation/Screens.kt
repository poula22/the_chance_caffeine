package com.thechance.caffeine.presentation.feature.home.navigation

import kotlinx.serialization.Serializable

@Serializable
object BringMyCoffeeRoute

@Serializable
object ChooseYourCoffeeRoute

@Serializable
data class CoffeeDetailsRoute(val coffee: String)

@Serializable
data class CoffeeIsReadyRoute(val coffee: String)

@Serializable
object ThankYouRoute