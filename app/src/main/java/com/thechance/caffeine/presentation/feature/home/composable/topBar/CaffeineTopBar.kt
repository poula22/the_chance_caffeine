package com.thechance.caffeine.presentation.feature.home.composable.topBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thechance.caffeine.presentation.feature.home.event.ComposeComponent

@Composable
fun CaffeineTopBar(
    modifier: Modifier = Modifier,
    leadingIcon: ComposeComponent? = null,
    tailingIcon: ComposeComponent? = null,
    title: String? = null
) {
    val hasSpacer by rememberUpdatedState(leadingIcon != null && title != null)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            AnimatedVisibility(
                visible = leadingIcon != null,
            ) {
                leadingIcon?.invoke()
            }

            if (hasSpacer) Spacer(modifier = Modifier.padding(end = 12.dp))

            AnimatedVisibility(
                modifier = Modifier.align(Alignment.CenterVertically),
                visible = title != null
            ) {
                title?.let { Text(text = title, style = MaterialTheme.typography.titleMedium) }
            }
        }

        AnimatedVisibility(
            visible = tailingIcon != null,
        ) {
            tailingIcon?.invoke()
        }
    }
}