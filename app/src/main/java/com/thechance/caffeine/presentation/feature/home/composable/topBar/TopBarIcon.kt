package com.thechance.caffeine.presentation.feature.home.composable.topBar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.thechance.caffeine.presentation.feature.home.event.OnClick
import com.thechance.caffeine.presentation.theme.IconBackground

@Composable
fun TopBarIcon(
    icon: Painter,
    contentDescription: String,
    onIconClick: OnClick,
    modifier: Modifier = Modifier,
    size: Dp = 48.dp,
    contentPadding: Dp = 12.dp,
    backgroundColor: Color = IconBackground
) {
    Icon(
        painter = icon,
        contentDescription = contentDescription,
        tint = Color.Unspecified,
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color = backgroundColor)
            .clickable(onClick = onIconClick)
            .padding(contentPadding)
    )
}