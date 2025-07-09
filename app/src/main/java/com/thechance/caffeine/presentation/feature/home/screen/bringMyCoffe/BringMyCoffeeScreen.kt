package com.thechance.caffeine.presentation.feature.home.screen.bringMyCoffe

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thechance.caffeine.R
import com.thechance.caffeine.presentation.feature.home.composable.button.CaffeineButton
import com.thechance.caffeine.presentation.feature.home.composable.topBar.CaffeineTopBar
import com.thechance.caffeine.presentation.feature.home.composable.topBar.TopBarIcon
import com.thechance.caffeine.presentation.feature.home.composable.topBar.TopBarImage
import com.thechance.caffeine.presentation.feature.home.composable.modifier.topBarModifier
import com.thechance.caffeine.presentation.feature.home.event.OnClick
import com.thechance.caffeine.presentation.theme.Background
import com.thechance.caffeine.presentation.theme.CaffeineTheme
import com.thechance.caffeine.presentation.theme.TextColorLight

@Composable
fun BringMyCoffeeScreen(
    onImageClick: OnClick,
    onIconClick: OnClick,
    onButtonClick: OnClick,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "stars animation")

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
                        onIconClick = onIconClick
                    )
                }
            )
        }
    ) {
        Box(
            Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    WelcomeSection(
                        infiniteTransition = infiniteTransition,
                        modifier = Modifier.padding(top = 24.dp)
                    )
                }

                item {
                    GhostImage(
                        infiniteTransition = infiniteTransition,
                        image = painterResource(R.drawable.im_ghost),
                        modifier = Modifier.padding(top = 31.dp)
                    )
                }
            }

            CaffeineButton(
                modifier = Modifier
                    .padding(bottom = 50.dp, start = 16.dp, end = 16.dp)
                    .align(Alignment.BottomCenter),
                text = "bring my coffee",
                iconPainter = painterResource(R.drawable.ic_coffee_button),
                onClick = onButtonClick
            )
        }
    }
}

@Composable
private fun WelcomeSection(
    infiniteTransition: InfiniteTransition,
    modifier: Modifier = Modifier
) {
    val startsAlpha by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 300, delayMillis = 200, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(modifier) {
        Text(
            text = "Hocus\n" +
                    "Pocus\n" +
                    "I Need Coffee\n" +
                    "to Focus",
            color = TextColorLight,
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(end = 15.dp, bottom = 2.dp)
        )

        Icon(
            painter = painterResource(R.drawable.ic_star),
            tint = Color.Unspecified,
            contentDescription = "star icon bottom",
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.BottomEnd)
                .graphicsLayer {
                    alpha = startsAlpha
                }
        )
        Icon(
            painter = painterResource(R.drawable.ic_star),
            tint = Color.Unspecified,
            contentDescription = "star icon bottom",
            modifier = Modifier
                .padding(end = 14.dp)
                .size(16.dp)
                .align(Alignment.TopEnd)
                .graphicsLayer {
                    alpha = startsAlpha
                }
        )

        Icon(
            painter = painterResource(R.drawable.ic_star),
            tint = Color.Unspecified,
            contentDescription = "star icon bottom",
            modifier = Modifier
                .padding(start = 10.dp, top = 65.dp)
                .size(16.dp)
                .align(Alignment.TopStart)
                .graphicsLayer {
                    alpha = startsAlpha
                }
        )
    }
}

@Composable
fun GhostImage(
    infiniteTransition: InfiniteTransition,
    image: Painter,
    modifier: Modifier = Modifier
) {
    val tweenSpec = remember {
        tween<Float>(1500, delayMillis = 10, easing = EaseOut)
    }

    val shadowAlpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.5f,
        label = "shadow alpha",
        animationSpec = infiniteRepeatable(
            animation = tweenSpec,
            repeatMode = RepeatMode.Reverse
        )
    )

    val movingValuePercentage by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        label = "moving animation",
        animationSpec = infiniteRepeatable(
            animation = tweenSpec,
            repeatMode = RepeatMode.Reverse
        )
    )

    val maxMovingValue = with(LocalDensity.current) { 10.dp.toPx() }

    Column(modifier) {
        Image(
            painter = image,
            contentDescription = "ghost image",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(244.dp)
                .graphicsLayer {
                    translationY = -movingValuePercentage * maxMovingValue
                }
        )

        Box(
            Modifier
                .padding(top = 2.44.dp)
                .height(43.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Box(
                Modifier
                    .size(width = 177.31.dp, height = 27.65.dp)
                    .blur(12.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
                    .clip(CircleShape)
                    .graphicsLayer {
                        alpha = shadowAlpha
                    }
                    .drawBehind {
                        drawOval(
                            color = Color(0x241F1F1F)
                        )
                    }
            )
        }
    }
}

@Preview
@Composable
private fun BringMyCoffeePreview() {
    CaffeineTheme {
        BringMyCoffeeScreen(
            onImageClick = {},
            onIconClick = {},
            onButtonClick = {},
            modifier = Modifier.background(Background)
        )
    }
}