package com.thechance.caffeine.presentation.feature.home.composable.topBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TopBarImage(
    image: Painter,
    contentDescription: String,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp
) {
    Image(
        painter = image,
        contentDescription = contentDescription,
        modifier = modifier
            .size(size)
            .clickable(onClick = onImageClick)
    )
}