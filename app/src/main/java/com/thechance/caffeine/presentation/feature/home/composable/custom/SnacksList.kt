package com.thechance.caffeine.presentation.feature.home.composable.custom

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.thechance.caffeine.R
import com.thechance.caffeine.presentation.feature.home.model.HomeScreenState
import com.thechance.caffeine.presentation.theme.CaffeineTheme
import kotlin.math.abs

@Composable
fun SnacksList(
    list: List<HomeScreenState.ChooseYourSnackUiState.SnackUi>,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState(1) { list.size }
) {
    VerticalPager(
        modifier = modifier.animateContentSize(),
        state = pagerState,
        contentPadding = PaddingValues(vertical = 160.dp),
        pageSize = PageSize.Fixed(260.dp),
        beyondViewportPageCount = pagerState.pageCount,
        pageSpacing = -20.dp,
        horizontalAlignment = Alignment.Start
    ) { index ->
        val item = list[index]
        val itemTransition = updateTransition(pagerState.currentPage)
        val itemScale by itemTransition.animateFloat(
            label = "item scale",
            transitionSpec = { tween(durationMillis = 300, easing = LinearEasing) },
            targetValueByState = { styleIndex ->
                when (index) {
                    styleIndex - 1 -> 0.77f //todo
                    styleIndex + 1 -> 1f
                    else -> 1f
                }
            }
        )
//
        val itemRotation by itemTransition.animateFloat(
            label = "item rotation",
            transitionSpec = { tween(durationMillis = 300, easing = LinearEasing) },
            targetValueByState = { styleIndex ->
                when {
                    styleIndex == index -> 0f
                    styleIndex - index > 0 -> -8.91f //todo
                    else -> 8.91f
                }
            }
        )

        val itemTranslationX by itemTransition.animateDp(
            label = "item translationY",
            transitionSpec = { tween(durationMillis = 200, easing = LinearEasing) },
            targetValueByState = { styleIndex ->
                when (index) {
                    styleIndex -> 0.dp
                    styleIndex - 1 -> -50.dp
                    styleIndex + 1 -> -32.dp
                    else -> -320.dp
                }
            }
        )

        SnackItem(
            snackUi = item,
            modifier = Modifier
                .size(260.dp, 274.dp)
                .then(
                    if (index == pagerState.currentPage) Modifier.clickable(onClick = { onClick(item.image) })
                    else Modifier
                )
                .graphicsLayer {
                    scaleX = itemScale
                    scaleY = itemScale
                    rotationZ = itemRotation
                    translationX = itemTranslationX.toPx()
                    translationY =
                        abs(pagerState.currentPage - index) * 260.dp.toPx() - abs(pagerState.currentPage - index) * 260.dp.toPx() * itemScale
                }
        )
    }
}

@Composable
private fun SnackItem(
    snackUi: HomeScreenState.ChooseYourSnackUiState.SnackUi,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .clip(RoundedCornerShape(32.dp))
            .background(Color(0xFFF5F5F5))
    ) {
        Image(
            painter = painterResource(snackUi.image),
            contentDescription = "snack image",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(149.dp)
                .align(Alignment.Center)
        )
    }
}


@Preview
@Composable
private fun SnackListPreview() {
    val shape = LocalDensity.current.archShape()

    CaffeineTheme {
        Column(Modifier.height(620.dp)) {
            SnacksList(
                onClick = {},
                list = listOf(
                    HomeScreenState.ChooseYourSnackUiState.SnackUi(
                        title = "cupcake",
                        image = R.drawable.im_cupcake
                    ),
                    HomeScreenState.ChooseYourSnackUiState.SnackUi(
                        title = "cinamon",
                        image = R.drawable.im_cinamon
                    ),
                    HomeScreenState.ChooseYourSnackUiState.SnackUi(
                        title = "cookies",
                        image = R.drawable.im_cockies
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
                )
            )
        }
    }
}

fun Density.archShape(): Shape = GenericShape { size, _ ->
    arcTo(
//        rect = Rect(center = size.center, 260.dp.toPx()),
        rect = Rect(topLeft = Offset(0f, 0f), bottomRight = Offset(260.dp.toPx(), 1200.dp.toPx())),
        startAngleDegrees = 0f,
        sweepAngleDegrees = -350f,
        forceMoveTo = false
    )
}