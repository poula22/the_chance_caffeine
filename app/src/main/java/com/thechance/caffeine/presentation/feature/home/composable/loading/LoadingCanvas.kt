package com.thechance.caffeine.presentation.feature.home.composable.loading

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.thechance.caffeine.presentation.theme.CaffeineTheme
import kotlinx.coroutines.delay

@Composable
fun LoadingCanvas(modifier: Modifier = Modifier) {
    val density = LocalDensity.current

    val pathPoints = remember {
        listOf(
            DpOffset(x = 53.dp, y = 0.dp),
            DpOffset(x = 98.dp, y = 32.dp),
            DpOffset(x = 143.dp, y = 0.dp),
            DpOffset(x = 188.dp, y = 32.dp),
            DpOffset(x = 233.dp, y = 0.dp),
            DpOffset(x = 278.dp, y = 32.dp),
            DpOffset(x = 323.dp, y = 0.dp),
            DpOffset(x = 368.dp, y = 32.dp)
        )
    }

    val controlPoints = remember {
        listOf(
            DpOffset(x = 30.dp, y = 32.dp),
            DpOffset(x = 30.dp, y = 0.dp),
            DpOffset(x = 75.dp, y = 0.dp),
            DpOffset(x = 75.dp, y = 32.dp),
            DpOffset(x = 120.dp, y = 32.dp),
            DpOffset(x = 120.dp, y = 0.dp),
            DpOffset(x = 165.dp, y = 0.dp),
            DpOffset(x = 165.dp, y = 32.dp),
            DpOffset(x = 210.dp, y = 32.dp),
            DpOffset(x = 210.dp, y = 0.dp),
            DpOffset(x = 255.dp, y = 0.dp),
            DpOffset(x = 255.dp, y = 32.dp),
            DpOffset(x = 300.dp, y = 32.dp),
            DpOffset(x = 300.dp, y = 0.dp),
            DpOffset(x = 355.dp, y = 0.dp),
            DpOffset(x = 355.dp, y = 32.dp)
        )
    }

    val path = Path().apply {
        with(density) {
            moveTo(0f, 0f)

            cubicTo(
                x1 = 2.dp.toPx(),
                y1 = 0f,
                x2 = 2.dp.toPx(),
                y2 = 32.dp.toPx(),
                x3 = 8.dp.toPx(),
                y3 = 32.dp.toPx()
            )

            cubicTo(
                x1 = controlPoints[0].x.toPx(),
                y1 = controlPoints[0].y.toPx(),
                x2 = controlPoints[1].x.toPx(),
                y2 = controlPoints[1].y.toPx(),
                x3 = pathPoints[0].x.toPx(),
                y3 = pathPoints[0].y.toPx()
            )

            cubicTo(
                x1 = controlPoints[2].x.toPx(),
                y1 = controlPoints[2].y.toPx(),
                x2 = controlPoints[3].x.toPx(),
                y2 = controlPoints[3].y.toPx(),
                x3 = pathPoints[1].x.toPx(),
                y3 = pathPoints[1].y.toPx()
            )

            cubicTo(
                x1 = controlPoints[4].x.toPx(),
                y1 = controlPoints[4].y.toPx(),
                x2 = controlPoints[5].x.toPx(),
                y2 = controlPoints[5].y.toPx(),
                x3 = pathPoints[2].x.toPx(),
                y3 = pathPoints[2].y.toPx()
            )

            cubicTo(
                x1 = controlPoints[6].x.toPx(),
                y1 = controlPoints[6].y.toPx(),
                x2 = controlPoints[7].x.toPx(),
                y2 = controlPoints[7].y.toPx(),
                x3 = pathPoints[3].x.toPx(),
                y3 = pathPoints[3].y.toPx()
            )

            cubicTo(
                x1 = controlPoints[8].x.toPx(),
                y1 = controlPoints[8].y.toPx(),
                x2 = controlPoints[9].x.toPx(),
                y2 = controlPoints[9].y.toPx(),
                x3 = pathPoints[4].x.toPx(),
                y3 = pathPoints[4].y.toPx()
            )

            cubicTo(
                x1 = controlPoints[10].x.toPx(),
                y1 = controlPoints[10].y.toPx(),
                x2 = controlPoints[11].x.toPx(),
                y2 = controlPoints[11].y.toPx(),
                x3 = pathPoints[5].x.toPx(),
                y3 = pathPoints[5].y.toPx()
            )

            cubicTo(
                x1 = controlPoints[12].x.toPx(),
                y1 = controlPoints[12].y.toPx(),
                x2 = controlPoints[13].x.toPx(),
                y2 = controlPoints[13].y.toPx(),
                x3 = pathPoints[6].x.toPx(),
                y3 = pathPoints[6].y.toPx()
            )

            cubicTo(
                x1 = controlPoints[14].x.toPx(),
                y1 = controlPoints[14].y.toPx(),
                x2 = controlPoints[15].x.toPx(),
                y2 = controlPoints[15].y.toPx(),
                x3 = pathPoints[7].x.toPx(),
                y3 = pathPoints[7].y.toPx()
            )
        }
    }

    val pos = FloatArray(2)
    val tan = FloatArray(2)

    val pathPortion = remember {
        Animatable(initialValue = 0f)
    }

    val outputPath = android.graphics.Path()

    android.graphics.PathMeasure().apply {
        setPath(path.asAndroidPath(), false)
        getSegment(0f, pathPortion.value * length, outputPath, true)
        getPosTan(pathPortion.value * length, pos, tan)
    }

    var isAnimated by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = isAnimated) {
        if (isAnimated) {
            pathPortion.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    delayMillis = 10,
                    durationMillis = 3000,
                    easing = EaseIn
                )
            )
            isAnimated = false
        } else {
            pathPortion.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    delayMillis = 10,
                    durationMillis = 3000,
                    easing = EaseIn
                )
            )
            isAnimated = true
        }
    }

    Canvas(
        modifier = modifier
            .size(width = 368.dp, height = 32.dp)
    ) {
        drawPath(
            path = outputPath.asComposePath(),
            color = Color.Black,
            style = Stroke(2.dp.toPx()),
        )
    }
}


@Preview
@Composable
private fun LoadingCanvasPreview() {
    CaffeineTheme {
        LoadingCanvas(Modifier.fillMaxWidth())
    }
}