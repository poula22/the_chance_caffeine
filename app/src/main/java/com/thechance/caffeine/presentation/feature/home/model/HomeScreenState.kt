package com.thechance.caffeine.presentation.feature.home.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable

@Stable
data class HomeScreenState(
    val chooseYourCoffeeState: ChooseYourCoffeeUiState = ChooseYourCoffeeUiState(),
    val coffeeDetailsState: CoffeeDetailsUiState = CoffeeDetailsUiState(),
    val chooseYourSnackState: ChooseYourSnackUiState = ChooseYourSnackUiState()
) {
    @Stable
    data class ChooseYourCoffeeUiState(
        val coffeeList: List<CoffeeUi> = emptyList(),
        val userName: String = ""
    )

    @Stable
    data class CoffeeDetailsUiState(
        val coffeeTitle: String = "",
        @DrawableRes val coffeeImageRes: Int = 0,
        val selectedSizeIndex: Int = 1,
        val selectedBeanIndex: Int = 0,
        val availableSizes: List<Size> = listOf(Size.SMALL, Size.MEDIUM, Size.LARGE),
        val availableBeans: List<Beans> = listOf(Beans.LOW, Beans.MEDIUM, Beans.High),
        val isCoffeeReady: Boolean = false,
        val isTakeAway: Boolean = false
    ) {
        enum class Size(val symbol: String, val value: Int){
            SMALL("S", 150),
            MEDIUM("M", 200),
            LARGE("L", 400)
        }

        enum class Beans(val text: String) {
            LOW("Low"),
            MEDIUM("Medium"),
            High("High")
        }
    }

    @Stable
    data class CoffeeIsReadyUiState(
        val isTakeAway: Boolean,
        val coffeeUi: CoffeeUi,
        @DrawableRes val cover: Int
    )

    @Stable
    data class ChooseYourSnackUiState(
        val snackList: List<SnackUi> = emptyList()
    ) {
        data class SnackUi(
            val title: String,
            @DrawableRes val image: Int
        )
    }

    data class CoffeeUi(
        val title: String,
        @DrawableRes val image: Int
    )
}