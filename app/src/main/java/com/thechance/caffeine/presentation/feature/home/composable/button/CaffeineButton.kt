package com.thechance.caffeine.presentation.feature.home.composable.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun CaffeineButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconPainter: Painter,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp
        ),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = Color(0xFF1F1F1F)
        ),
        shape = CircleShape,
        contentPadding = PaddingValues(
            vertical = 16.dp,
            horizontal = 32.dp
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(0.87f)
        )

        Icon(
            modifier = Modifier
                .padding(start = 8.dp)
                .size(24.dp),
            painter = iconPainter,
            contentDescription = "icon",
            tint = Color.Unspecified
        )
    }
}