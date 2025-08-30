package com.thechance.caffeine.presentation.feature.home.navigation

import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.thechance.caffeine.R
import com.thechance.caffeine.presentation.feature.home.model.HomeScreenState
import com.thechance.caffeine.presentation.feature.home.screen.bringMyCoffe.BringMyCoffeeScreen
import com.thechance.caffeine.presentation.feature.home.screen.chooseYourCoffee.ChooseYourCoffeeScreen
import com.thechance.caffeine.presentation.feature.home.screen.chooseYourSnack.ChooseYourSnackScreen
import com.thechance.caffeine.presentation.feature.home.screen.coffeIsReady.CoffeeIsReadyScreen
import com.thechance.caffeine.presentation.feature.home.screen.coffeeDetails.CoffeeDetailsScreen
import com.thechance.caffeine.presentation.feature.home.screen.thankYou.ThankYouScreen
import com.thechance.caffeine.presentation.feature.home.utils.getCoverImageResourceFromCoffeeName
import com.thechance.caffeine.presentation.feature.home.utils.getCupImageResourceFromCoffeeName
import com.thechance.caffeine.presentation.theme.Background

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
                modifier = Modifier.background(Background),
                onImageClick = { navController.navigate(ChooseYourCoffeeRoute) },
                onIconClick = { navController.navigate(ChooseYourCoffeeRoute) },
                onButtonClick = { navController.navigate(ChooseYourCoffeeRoute) },
            )
        }

        composable<ChooseYourCoffeeRoute> {
            ChooseYourCoffeeScreen(
                modifier = Modifier.background(Background),
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

        composable<CoffeeDetailsRoute>(
            enterTransition = { slideInVertically(spring()) { it } },
            exitTransition = { slideOutVertically(spring()) { it } }
        ) {
            val route = it.toRoute<CoffeeDetailsRoute>()
            val state = remember {
                mutableStateOf(
                    HomeScreenState.CoffeeDetailsUiState(
                        coffeeTitle = route.coffee,
                        coffeeImageRes = getCupImageResourceFromCoffeeName(route.coffee)
                    )
                )
            }
            var isAddingBeans by remember { mutableStateOf(true) }

            CoffeeDetailsScreen(
                modifier = Modifier.background(Background),
                coffeeDetailsUiState = state.value,
                isAddingBeans = isAddingBeans,
                onBeanClick = {
                    isAddingBeans = it > state.value.selectedBeanIndex
                    state.value = state.value.copy(selectedBeanIndex = it)
                },
                onSizeClick = { state.value = state.value.copy(selectedSizeIndex = it) },
                onBackArrowClicked = { navController.navigateUp() },
                onNavigate = { navController.navigate(CoffeeIsReadyRoute(route.coffee)) },
            )
        }

        composable<CoffeeIsReadyRoute>(
            enterTransition = { slideInVertically(spring()) { it } },
            exitTransition = { slideOutVertically(spring()) { it } }
        ) {
            val route = it.toRoute<CoffeeIsReadyRoute>()
            val cup = getCupImageResourceFromCoffeeName(route.coffee)
            val cover = getCoverImageResourceFromCoffeeName(route.coffee)

            var coffeeIsReadyUiState by remember {
                mutableStateOf(
                    HomeScreenState.CoffeeIsReadyUiState(
                        isTakeAway = true,
                        coffeeUi = HomeScreenState.CoffeeUi(
                            title = route.coffee,
                            image = cup
                        ),
                        cover = cover
                    )
                )
            }

            CoffeeIsReadyScreen(
                modifier = Modifier.background(Background),
                coffeeIsReadyUiState = coffeeIsReadyUiState,
                onTakeAwaySwitchClicked = {
                    coffeeIsReadyUiState =
                        coffeeIsReadyUiState.copy(isTakeAway = !coffeeIsReadyUiState.isTakeAway)
                },
                onTakeAwayClick = { navController.navigate(SnackScreenRoute) },
                onCancelClicked = { navController.navigateUp() }
            )
        }
        composable<SnackScreenRoute> {
            val chooseYourSnackUiState = HomeScreenState.ChooseYourSnackUiState(
                snackList = listOf(
                    HomeScreenState.ChooseYourSnackUiState.SnackUi(
                        title = "chocolate",
                        image = R.drawable.im_chocolate
                    ),
                    HomeScreenState.ChooseYourSnackUiState.SnackUi(
                        title = "cupcake",
                        image = R.drawable.im_cupcake
                    ),
                    HomeScreenState.ChooseYourSnackUiState.SnackUi(
                        title = "cookies",
                        image = R.drawable.im_cockies
                    ),
                    HomeScreenState.ChooseYourSnackUiState.SnackUi(
                        title = "cinamon",
                        image = R.drawable.im_cinamon
                    ),
                    HomeScreenState.ChooseYourSnackUiState.SnackUi(
                        title = "Oreo",
                        image = R.drawable.im_oreo
                    )
                )
            )

            ChooseYourSnackScreen(
                modifier = Modifier.fillMaxSize(),
                chooseYourSnackUiState = chooseYourSnackUiState,
                onSelectSnack = { navController.navigate(ThankYouRoute(it)) },
                onCancel = { navController.navigateUp() }
            )
        }

        composable<ThankYouRoute> {
            val snackRes = it.toRoute<ThankYouRoute>().snackRes

            ThankYouScreen(
                snackImagePainter = painterResource(snackRes),
                onCancel = { navController.navigateUp() },
                onThankYouClicked = {
                    navController.popBackStack(
                        route = BringMyCoffeeRoute,
                        inclusive = false
                    )
                }
            )
        }
    }

}