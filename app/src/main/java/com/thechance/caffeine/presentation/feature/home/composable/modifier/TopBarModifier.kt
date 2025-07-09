package com.thechance.caffeine.presentation.feature.home.composable.modifier

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Stable
fun Modifier.topBarModifier(): Modifier {
    return fillMaxWidth()
        .statusBarsPadding()
        .padding(
            top = 16.dp,
            start = 16.dp,
            end = 16.dp
        )
}