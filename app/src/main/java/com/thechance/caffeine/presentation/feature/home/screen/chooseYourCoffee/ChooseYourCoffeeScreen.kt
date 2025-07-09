package com.thechance.caffeine.presentation.feature.home.screen.chooseYourCoffee

import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thechance.caffeine.R
import com.thechance.caffeine.presentation.feature.home.composable.button.CaffeineButton
import com.thechance.caffeine.presentation.feature.home.composable.modifier.topBarModifier
import com.thechance.caffeine.presentation.feature.home.composable.topBar.CaffeineTopBar
import com.thechance.caffeine.presentation.feature.home.composable.topBar.TopBarIcon
import com.thechance.caffeine.presentation.feature.home.composable.topBar.TopBarImage
import com.thechance.caffeine.presentation.feature.home.event.OnClick
import com.thechance.caffeine.presentation.feature.home.model.HomeScreenState
import com.thechance.caffeine.presentation.theme.CaffeineTheme
import com.thechance.caffeine.presentation.theme.TextColorLight

@Composable
fun ChooseYourCoffeeScreen(
    coffeeState: HomeScreenState.ChooseYourCoffeeUiState,
    onImageClick: OnClick,
    onAddIconClick: (coffee: String) -> Unit,
    onCoffeeButtonClick: (coffee: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState { coffeeState.coffeeList.size }

    Scaffold(
        modifier = modifier,
        topBar = {
            CaffeineTopBar(
                modifier = Modifier.topBarModifier(),
                leadingIcon = {
                    TopBarImage(
                        image = painterResource(R.drawable.im_ghost_profile),
                        contentDescription = "profile image",
                        onImageClick = onImageClick
                    )
                },
                tailingIcon = {
                    TopBarIcon(
                        icon = painterResource(R.drawable.ic_add),
                        contentDescription = "add icon",
                        onIconClick = { onAddIconClick(coffeeState.coffeeList[pagerState.currentPage].title) }
                    )
                }
            )
        }
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Header(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp)
                )

                HorizontalPager(
                    modifier = Modifier
                        .padding(top = 56.dp)
                        .wrapContentHeight(),
                    state = pagerState,
                    reverseLayout = true,
                    pageSpacing = 8.dp,
                    contentPadding = PaddingValues(
                        horizontal = 80.dp
                    ),
                    pageSize = PageSize.Fixed(199.dp),
                    beyondViewportPageCount = pagerState.pageCount
                ) { coffeeIndex ->
                    val coffee = coffeeState.coffeeList[coffeeIndex]
                    val transition = updateTransition(targetState = pagerState.currentPage)
                    val coffeeImageSize by transition.animateDp(
                        transitionSpec = {
                            tween(durationMillis = 1000, easing = EaseOut)
                        },
                        label = "coffeeImageSize",
                        targetValueByState = { state ->
                            if (state == coffeeIndex) 244.dp else 150.dp
                        }
                    )
                    val brandImageSize by transition.animateDp(
                        transitionSpec = {
                            tween(durationMillis = 1000, easing = EaseOut)
                        },
                        label = "brandImageSize",
                        targetValueByState = { state ->
                            if (state == coffeeIndex) 66.dp else 40.dp
                        }
                    )

                    val brandImagePadding by transition.animateDp(
                        transitionSpec = {
                            tween(durationMillis = 1000, easing = EaseOut)
                        },
                        label = "brandImageSize",
                        targetValueByState = { state ->
                            if (state == coffeeIndex) 62.dp else 35.dp
                        }
                    )

                    Box(Modifier.size(width = 199.dp, height = 298.dp)) {
                        Column(
                            modifier = Modifier.align(Alignment.BottomStart),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Box {
                                Image(
                                    painter = painterResource(coffee.image),
                                    contentDescription = "coffee image",
                                    contentScale = ContentScale.FillHeight,
                                    modifier = Modifier.height(coffeeImageSize)
                                )

                                Image(
                                    painter = painterResource(R.drawable.im_brand_logo),
                                    contentDescription = "brand logo",
                                    contentScale = ContentScale.FillHeight,
                                    modifier = Modifier
                                        .padding(bottom = brandImagePadding)
                                        .height(brandImageSize)
                                        .align(Alignment.BottomCenter)
                                )
                            }
                            Text(
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .fillMaxWidth(),
                                text = coffee.title,
                                style = MaterialTheme.typography.bodyLarge,
                                color = TextColorLight,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            CaffeineButton(
                text = "Continue",
                iconPainter = painterResource(R.drawable.ic_arrow_continue),
                onClick = { onCoffeeButtonClick(coffeeState.coffeeList[pagerState.currentPage].title) },
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = "Good Morning",
            color = Color(0xFFB3B3B3),
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "Poula ☀",
            color = Color(0xFF3B3B3B),
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "What would you like to drink today?",
            color = TextColorLight,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Preview
@Composable
private fun ChooseYourCoffeePreview() {
    CaffeineTheme {
        val uiState = HomeScreenState.ChooseYourCoffeeUiState(
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
        ChooseYourCoffeeScreen(
            coffeeState = uiState,
            onImageClick = {},
            onAddIconClick = {},
            onCoffeeButtonClick = {}
        )
    }
}