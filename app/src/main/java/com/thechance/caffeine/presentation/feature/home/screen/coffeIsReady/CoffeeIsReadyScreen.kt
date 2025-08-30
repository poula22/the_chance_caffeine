package com.thechance.caffeine.presentation.feature.home.screen.coffeIsReady

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.caffeine.R
import com.thechance.caffeine.presentation.feature.home.composable.button.CaffeineButton
import com.thechance.caffeine.presentation.feature.home.composable.modifier.topBarModifier
import com.thechance.caffeine.presentation.feature.home.composable.topBar.CaffeineTopBar
import com.thechance.caffeine.presentation.feature.home.composable.topBar.TopBarIcon
import com.thechance.caffeine.presentation.feature.home.event.OnClick
import com.thechance.caffeine.presentation.feature.home.model.HomeScreenState
import com.thechance.caffeine.presentation.theme.CaffeineTheme
import com.thechance.caffeine.presentation.theme.TextColorLight
import kotlinx.coroutines.launch

@Composable
fun CoffeeIsReadyScreen(
    coffeeIsReadyUiState: HomeScreenState.CoffeeIsReadyUiState,
    onTakeAwaySwitchClicked: OnClick,
    onTakeAwayClick: OnClick,
    onCancelClicked: OnClick,
    modifier: Modifier = Modifier
) {
    val coverOffsetY = remember { Animatable(0f) }
    val coverScaleX = remember { Animatable(0.7f) }

    val takeAwayTransition =
        updateTransition(coffeeIsReadyUiState.isTakeAway, label = "take away transition")
    val takeAwayRowColor by takeAwayTransition.animateColor(
        transitionSpec = { tween(delayMillis = 300, easing = EaseIn) },
        label = "row color",
        targetValueByState = { isTakeAway ->
            if (isTakeAway) Color(0xFF7C351B) else Color(0xFFFFEEE7)
        }
    )
    val takeAwayTextColor by takeAwayTransition.animateColor(
        transitionSpec = { tween(delayMillis = 300, easing = EaseIn) },
        label = "text color",
        targetValueByState = { isTakeAway ->
            if (isTakeAway) Color.White.copy(0.6f) else Color.Black.copy(0.6f)
        },
    )
    val takeAwayImageAlignment by takeAwayTransition.animateFloat(
        transitionSpec = { tween(delayMillis = 300, easing = EaseIn) },
        label = "image alignment",
        targetValueByState = { isTakeAway -> if (isTakeAway) -1f else 1f },
    )
    val takeAwayImageRotation by takeAwayTransition.animateFloat(
        transitionSpec = { tween(delayMillis = 300, easing = EaseIn) },
        label = "image rotation",
        targetValueByState = { isTakeAway -> if (isTakeAway) 0f else 360f }
    )
    val takeAwaySwitchTextPadding by takeAwayTransition.animateDp(
        transitionSpec = { tween(delayMillis = 300, easing = EaseIn) },
        label = "image rotation",
        targetValueByState = { isTakeAway -> if (isTakeAway) 14.dp else 5.dp }
    )
    val takeAwaySwitchText by remember {
        derivedStateOf {
            if (takeAwayTransition.currentState) "ON" else "OFF"
        }
    }

    LaunchedEffect(Unit) {
        launch {
            coverOffsetY.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 600,
                    easing = EaseIn
                )
            )
        }

        launch {
            coverScaleX.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 600,
                    easing = EaseIn
                )
            )
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            CaffeineTopBar(
                modifier = Modifier.topBarModifier(),
                leadingIcon = {
                    TopBarIcon(
                        icon = painterResource(R.drawable.ic_cancel),
                        contentDescription = "cancel icon",
                        onIconClick = onCancelClicked
                    )
                }
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_confirm),
                contentDescription = "order confirmed",
                tint = Color.Unspecified,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(56.dp)
                    .dropShadow(
                        shape = CircleShape,
                        shadow = Shadow(
                            radius = 16.dp,
                            offset = DpOffset(0.dp, 6.dp),
                            color = Color(0x80B94B23)
                        )
                    )
                    .background(color = Color(0xFF7C351B), shape = CircleShape)
                    .clip(CircleShape)
                    .padding(12.dp)
            )

            Text(
                text = "Your coffee is ready,\n" + "Enjoy",
                textAlign = TextAlign.Center,
                color = TextColorLight,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 22.sp,
                    lineHeight = 22.sp
                ),
                modifier = Modifier.padding(top = 24.dp)
            )

            Box(Modifier.padding(top = 17.dp)) {
                Image(
                    painter = painterResource(coffeeIsReadyUiState.coffeeUi.image),
                    contentDescription = "coffee cup",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .padding(top = 22.dp)
                        .height(300.dp)
                        .width(245.dp)
                        .offset(x = 8.5.dp)
                        .graphicsLayer {
                            alpha = coverOffsetY.value
                        }
                )

                Image(
                    painter = painterResource(R.drawable.im_brand_logo),
                    contentDescription = "coffee logo",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 139.dp)
                        .height(64.dp)
                        .graphicsLayer {
                            alpha = coverOffsetY.value
                        }
                )

                Image(
                    painter = painterResource(coffeeIsReadyUiState.cover),
                    contentDescription = "coffee cover",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(69.dp)
                        .width(260.dp)
                        .graphicsLayer {
                            translationY = (-300 + 300 * coverOffsetY.value).dp.toPx()
                            scaleX = coverScaleX.value
                        }
                )
            }

            Spacer(Modifier.weight(1f))

            Row {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(takeAwayRowColor)
                        .height(40.dp)
                        .width(78.dp)
                        .combinedClickable(
                            onClick = onTakeAwaySwitchClicked,
                            onDoubleClick = {},
                            onLongClick = {}
                        )
                ) {
                    Text(
                        text = takeAwaySwitchText,
                        color = takeAwayTextColor,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .align(
                                BiasAlignment(takeAwayImageAlignment * -1, 0f)
                            )
                            .padding(
                                start = 14.dp,
                                end = takeAwaySwitchTextPadding
                            )
                    )

                    Image(
                        painter = painterResource(R.drawable.im_take_away),
                        contentDescription = "take away",
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .align(
                                BiasAlignment(takeAwayImageAlignment, 0f)
                            )
                            .clip(CircleShape)
                            .height(40.dp)
                            .graphicsLayer {
                                rotationZ = takeAwayImageRotation
                            }
                    )
                }

                Text(
                    text = "Take Away",
                    color = TextColorLight.copy(0.7f),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp,
                        lineHeight = 14.sp
                    ),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(Alignment.CenterVertically)
                )
            }

            CaffeineButton(
                text = "Take snack",
                iconPainter = painterResource(R.drawable.ic_arrow_continue),
                onClick = onTakeAwayClick,
                modifier = Modifier.padding(top = 16.dp, bottom = 50.dp)
            )
        }

    }
}

@Preview
@Composable
private fun CoffeeIsReadyPreview() {
    CaffeineTheme {
        var state by remember {
            mutableStateOf(
                HomeScreenState.CoffeeIsReadyUiState(
                    isTakeAway = true,
                    coffeeUi = HomeScreenState.CoffeeUi(
                        title = "Black",
                        image = R.drawable.macchiato_empty_cup
                    ),
                    cover = R.drawable.macchiato_cover
                )
            )
        }
        CoffeeIsReadyScreen(
            modifier = Modifier.fillMaxSize(),
            coffeeIsReadyUiState = state,
            onTakeAwaySwitchClicked = { state = state.copy(isTakeAway = !state.isTakeAway) },
            onTakeAwayClick = {},
            onCancelClicked = {}
        )
    }
}