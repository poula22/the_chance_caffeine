package com.thechance.caffeine.presentation.feature.home.screen.coffeeDetails

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastForEachIndexed
import androidx.compose.ui.zIndex
import com.thechance.caffeine.R
import com.thechance.caffeine.presentation.feature.home.composable.button.CaffeineButton
import com.thechance.caffeine.presentation.feature.home.composable.loading.LoadingCanvas
import com.thechance.caffeine.presentation.feature.home.composable.modifier.topBarModifier
import com.thechance.caffeine.presentation.feature.home.composable.topBar.CaffeineTopBar
import com.thechance.caffeine.presentation.feature.home.composable.topBar.TopBarIcon
import com.thechance.caffeine.presentation.feature.home.event.OnClick
import com.thechance.caffeine.presentation.feature.home.model.HomeScreenState
import com.thechance.caffeine.presentation.theme.Background
import com.thechance.caffeine.presentation.theme.CaffeineTheme
import com.thechance.caffeine.presentation.theme.TextColorLight
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CoffeeDetailsScreen(
    coffeeDetailsUiState: HomeScreenState.CoffeeDetailsUiState,
    onSizeClick: (Int) -> Unit,
    onBeanClick: (Int) -> Unit,
    onNavigate: OnClick,
    onBackArrowClicked: OnClick,
    modifier: Modifier = Modifier,
    isAddingBeans: Boolean,
) {
    var isLoading by remember { mutableStateOf(false) }
    val transition = updateTransition(
        targetState = coffeeDetailsUiState.availableSizes[coffeeDetailsUiState.selectedSizeIndex]
    )
    val coffeeImageSize by transition.animateDp(transitionSpec = {
        tween(durationMillis = 300, easing = EaseOut)
    }, label = "coffeeImageSize", targetValueByState = { state ->
        when (state) {
            HomeScreenState.CoffeeDetailsUiState.Size.SMALL -> 188.dp
            HomeScreenState.CoffeeDetailsUiState.Size.MEDIUM -> 244.dp
            HomeScreenState.CoffeeDetailsUiState.Size.LARGE -> 300.dp
        }
    })
    val brandImageSize by transition.animateDp(transitionSpec = {
        tween(durationMillis = 300, easing = EaseOut)
    }, label = "brandImageSize", targetValueByState = { state ->
        when (state) {
            HomeScreenState.CoffeeDetailsUiState.Size.SMALL -> 40.dp
            HomeScreenState.CoffeeDetailsUiState.Size.MEDIUM -> 64.dp
            HomeScreenState.CoffeeDetailsUiState.Size.LARGE -> 64.dp
        }
    })
    val brandImagePadding by transition.animateDp(transitionSpec = {
        tween(durationMillis = 300, easing = EaseOut)
    }, label = "brand Image Padding", targetValueByState = { state ->
        when (state) {
            HomeScreenState.CoffeeDetailsUiState.Size.SMALL -> 75.dp
            HomeScreenState.CoffeeDetailsUiState.Size.MEDIUM -> 90.dp
            HomeScreenState.CoffeeDetailsUiState.Size.LARGE -> 118.dp
        }
    })
    val beanImageYOffset = remember { Animatable(0f) }
    val beanImageAlpha = remember { Animatable(0f) }
    val beanImageScale = remember { Animatable(0f) }

    val updatedOnNavigate by rememberUpdatedState(onNavigate)

    val density = LocalDensity.current

    LaunchedEffect(key1 = isLoading) {
        if (isLoading) {
            delay(4000)
            updatedOnNavigate()
        }
    }

    LaunchedEffect(coffeeDetailsUiState.selectedBeanIndex) {
        val (startYOffset, endYOffset, startScale, endScale) = with(density) {
            val offset = when (coffeeDetailsUiState.selectedSizeIndex) {
                0 -> 123.dp.toPx()
                1 -> 95.dp.toPx()
                else -> 70.dp.toPx()
            }
            if (isAddingBeans) {
                listOf(-200.dp.toPx(), offset, 1f, 0.5f)
            } else {
                listOf(offset, -200.dp.toPx(), 0.5f, 1f)
            }
        }
        val job1 = launch {
            beanImageYOffset.snapTo(startYOffset)
        }
        val job2 = launch {
            beanImageScale.snapTo(startScale)
        }
        val job3 = launch {
            beanImageAlpha.snapTo(1f)
        }
        val job4 = launch {
            beanImageYOffset.animateTo(
                targetValue = endYOffset,
                animationSpec = tween(durationMillis = 600, easing = EaseOut)
            )
        }
        val job5 = launch {
            beanImageScale.animateTo(
                targetValue = endScale,
                animationSpec = tween(durationMillis = 600, easing = EaseOut)
            )
        }

        job1.join()
        job2.join()
        job3.join()
        job4.join()
        job5.join()
        beanImageAlpha.snapTo(targetValue = 0f)
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AnimatedVisibility(
                visible = !isLoading
            ) {
                CaffeineTopBar(
                    leadingIcon = {
                        TopBarIcon(
                            icon = painterResource(R.drawable.ic_arrow),
                            contentDescription = "back arrow",
                            onIconClick = onBackArrowClicked
                        )
                    },
                    title = coffeeDetailsUiState.coffeeTitle,
                    modifier = Modifier.topBarModifier()
                )
            }

        }
    ) {
        Box(
            Modifier
                .padding(it)
                .fillMaxSize()
                .zIndex(2f)
        ) {
            Image(
                painter = painterResource(R.drawable.im_coffe_beans),
                contentDescription = "beans",
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.TopCenter)
                    .graphicsLayer {
                        translationY = beanImageYOffset.value
                        scaleX = beanImageScale.value
                        scaleY = beanImageScale.value
                        alpha = beanImageAlpha.value
                    }
            )

            Column(
                Modifier
                    .padding(top = 101.dp)
                    .fillMaxSize()
            ) {

                Column(Modifier.verticalScroll(rememberScrollState())) {

                    Box(
                        Modifier
                            .height(341.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "${coffeeDetailsUiState.availableSizes[coffeeDetailsUiState.selectedSizeIndex].value} ML",
                            color = Color.Black.copy(0.6f),
                            style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp),
                            modifier = Modifier
                                .padding(start = 16.dp, top = 64.dp)
                                .align(Alignment.TopStart)
                        )

                        Box(
                            Modifier
                                .padding(bottom = 20.dp, top = 21.dp)
                                .align(Alignment.Center)
                        ) {

                            Image(
                                painter = painterResource(coffeeDetailsUiState.coffeeImageRes),
                                contentDescription = "coffee image",
                                contentScale = ContentScale.FillHeight,
                                modifier = Modifier
                                    .height(coffeeImageSize)
                                    .align(Alignment.TopStart)
                            )

                            Image(
                                painter = painterResource(R.drawable.im_brand_logo),
                                contentDescription = "Coffee cup",
                                contentScale = ContentScale.FillHeight,
                                modifier = Modifier
                                    .padding(bottom = brandImagePadding)
                                    .size(brandImageSize)
                                    .align(Alignment.BottomCenter)
                            )
                        }
                    }

                    AnimatedVisibility(
                        modifier = Modifier.fillMaxWidth(),
                        visible = !isLoading
                    ) {
                        Column {
                            SizeSection(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                selectedSizeIndex = coffeeDetailsUiState.selectedSizeIndex,
                                availableSizes = coffeeDetailsUiState.availableSizes.map { it.symbol },
                                onSizeClick = onSizeClick
                            )

                            BeansSection(
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .align(Alignment.CenterHorizontally),
                                availableBeans = coffeeDetailsUiState.availableBeans.map { it.text },
                                selectedBeanIndex = coffeeDetailsUiState.selectedBeanIndex,
                                onBeanClick = onBeanClick
                            )
                        }
                    }
                }

                Spacer(Modifier.weight(1f))

                AnimatedContent(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    targetState = isLoading
                ) { isLoadingState ->

                    if (isLoadingState) {
                        Column {
                            Spacer(Modifier.weight(1f))

                            LoadingCanvas(
                                Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                            )

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .padding(top = 37.dp, bottom = 64.dp)
                                    .fillMaxWidth()
                            ) {

                                Text(
                                    text = "Almost Done",
                                    color = TextColorLight,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontSize = 22.sp, lineHeight = 22.sp
                                    )
                                )

                                Text(
                                    text = "Your coffee will be finish in",
                                    color = TextColorLight.copy(alpha = 0.6f),
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontSize = 16.sp, lineHeight = 16.sp
                                    )
                                )

                                Image(
                                    painter = painterResource(R.drawable.im_coffee_word),
                                    contentDescription = "coffee word",
                                    contentScale = ContentScale.FillHeight,
                                    modifier = Modifier
                                        .padding(top = 12.dp)
                                        .height(40.dp)
                                )
                            }
                        }
                    } else {
                        CaffeineButton(
                            text = "Continue",
                            iconPainter = painterResource(R.drawable.ic_arrow_continue),
                            onClick = { isLoading = true },
                            modifier = Modifier
                                .padding(bottom = 50.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SizeSection(
    selectedSizeIndex: Int,
    availableSizes: List<String>,
    onSizeClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val transition = updateTransition(selectedSizeIndex)

    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(Color(0xFFF5F5F5))
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        availableSizes.fastForEachIndexed { index, symbol ->
            val textColor by transition.animateColor(
                targetValueByState = { currentSelectedIndex ->
                    if (index == currentSelectedIndex) Color.White.copy(0.87f)
                    else Color(0x991F1F1F)
                },
                transitionSpec = { tween(durationMillis = 300, easing = EaseOut) },
                label = "text color"
            )

            val backgroundColor by transition.animateColor(
                targetValueByState = { currentSelectedIndex ->
                    if (index == currentSelectedIndex) Color(0xFF7C351B)
                    else Color(0xFFF5F5F5)
                },
                transitionSpec = { tween(durationMillis = 300, easing = EaseOut) },
                label = "background color"
            )

            Text(
                text = symbol,
                color = textColor,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.sp, lineHeight = 20.sp, textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .width(40.dp)
                    .background(backgroundColor, shape = CircleShape)
                    .then(
                        if (index == selectedSizeIndex) Modifier.dropShadow(
                            shape = CircleShape,
                            shadow = Shadow(
                                offset = DpOffset(x = 0.dp, y = 6.dp),
                                color = Color(0x80B94B23),
                                radius = 16.dp
                            )
                        ) else Modifier
                    )
                    .clip(CircleShape)
                    .clickable { onSizeClick(index) }
                    .padding(vertical = 8.dp))
        }
    }
}

@Composable
private fun BeansSection(
    availableBeans: List<String>,
    selectedBeanIndex: Int,
    onBeanClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val shadow = remember {
        Shadow(
            offset = DpOffset(x = 0.dp, y = 6.dp), color = Color(0x80B94B23), radius = 16.dp
        )
    }

    Column(modifier) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .width(152.dp)
                .clip(CircleShape)
                .background(Color(0xFFF5F5F5))
                .padding(8.dp)
        ) {
            availableBeans.fastForEachIndexed { index, text ->
                Column {
                    AnimatedContent(
                        modifier = Modifier.then(
                            if (index == selectedBeanIndex) Modifier.dropShadow(
                                shape = CircleShape, shadow = shadow
                            ) else Modifier
                        ), targetState = index == selectedBeanIndex, transitionSpec = {
                            fadeIn(
                                tween(
                                    durationMillis = 300,
                                    easing = EaseOut
                                )
                            ) togetherWith fadeOut(tween(durationMillis = 300, easing = EaseOut))
                        }) { isSelected ->
                        if (!isSelected) Text(
                            text = "",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .clickable {
                                    onBeanClick(index)
                                }
                        )
                        else Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_coffee_beans),
                            contentDescription = "selected Beans",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color(0xFF7C351B), shape = CircleShape)
                                .clip(CircleShape)
                                .padding(8.dp)
                        )
                    }
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 2.dp)
                .width(152.dp)
        ) {
            availableBeans.fastForEach { text ->
                Text(
                    text = text,
                    color = TextColorLight.copy(0.6f),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview
@Composable
private fun CoffeeDetailsPreview() {
    CaffeineTheme {
        var state by remember {
            mutableStateOf(
                HomeScreenState.CoffeeDetailsUiState(
                    coffeeTitle = "Espresso",
                    coffeeImageRes = R.drawable.im_coffe_espresso,
                    selectedSizeIndex = 2,
                    selectedBeanIndex = 2
                ),
            )
        }

        var isAddingBeans by remember { mutableStateOf(true) }


        CoffeeDetailsScreen(
            coffeeDetailsUiState = state,
            onSizeClick = { state = state.copy(selectedSizeIndex = it) },
            onBeanClick = {
                isAddingBeans = it > state.selectedBeanIndex
                state = state.copy(selectedBeanIndex = it)
            },
            onNavigate = {},
            onBackArrowClicked = {},
            modifier = Modifier.background(Background),
            isAddingBeans = isAddingBeans
        )
    }
}