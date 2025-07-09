package com.thechance.caffeine.presentation.feature.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.thechance.caffeine.R
import com.thechance.caffeine.presentation.feature.home.model.HomeScreenState
import com.thechance.caffeine.presentation.feature.home.screen.bringMyCoffe.BringMyCoffeeScreen
import com.thechance.caffeine.presentation.feature.home.screen.chooseYourCoffee.ChooseYourCoffeeScreen
import com.thechance.caffeine.presentation.feature.home.screen.coffeIsReady.CoffeeIsReadyScreen
import com.thechance.caffeine.presentation.feature.home.screen.coffeeDetails.CoffeeDetailsScreen
import com.thechance.caffeine.presentation.feature.home.utils.getImageResourceFromCoffeeName

@Composable
fun CaffeineNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = BringMyCoffeeRoute
    ) {
        composable<BringMyCoffeeRoute> {
            BringMyCoffeeScreen(
                onImageClick = { navController.navigate(ChooseYourCoffeeRoute) },
                onIconClick = { navController.navigate(ChooseYourCoffeeRoute) },
                onButtonClick = { navController.navigate(ChooseYourCoffeeRoute) },
            )
        }

        composable<ChooseYourCoffeeRoute> {
            ChooseYourCoffeeScreen(
                coffeeState = remember {
                    HomeScreenState.ChooseYourCoffeeUiState(
                        coffeeList = listOf(
                            HomeScreenState.CoffeeUi(
                                title = "Black",
                                image = R.drawable.im_coffe_black
                            ),
                            HomeScreenState.CoffeeUi(
                                title = "Latte",
                                image = R.drawable.im_coffe_latte
                            ),
                            HomeScreenState.CoffeeUi(
                                title = "Macchiato",
                                image = R.drawable.im_coffe_macchiato
                            ),
                            HomeScreenState.CoffeeUi(
                                title = "Espresso",
                                image = R.drawable.im_coffe_espresso
                            )
                        )
                    )
                },
                onImageClick = { navController.navigateUp() },
                onAddIconClick = { navController.navigate(CoffeeDetailsRoute(it)) },
                onCoffeeButtonClick = { navController.navigate(CoffeeDetailsRoute(it)) }
            )
        }

        composable<CoffeeDetailsRoute> {
            val route = it.toRoute<CoffeeDetailsRoute>()
            val state = remember {
                mutableStateOf(
                    HomeScreenState.CoffeeDetailsUiState(
                        coffeeTitle = route.coffee,
                        coffeeImageRes = getImageResourceFromCoffeeName(route.coffee)
                    )
                )
            }

            CoffeeDetailsScreen(
                coffeeDetailsUiState = state.value,
                onBeanClick = { state.value = state.value.copy(selectedBeanIndex = it) },
                onSizeClick = { state.value = state.value.copy(selectedSizeIndex = it) },
                onBackArrowClicked = { navController.navigateUp() },
                onNavigate = { navController.navigate(CoffeeIsReadyRoute(route.coffee)) }
            )
        }

        composable<CoffeeIsReadyRoute> {
            val route = it.toRoute<CoffeeIsReadyRoute>()
            CoffeeIsReadyScreen()
        }
    }

}