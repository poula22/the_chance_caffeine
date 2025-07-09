package com.thechance.caffeine.presentation.feature.home.screen.coffeIsReady

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.thechance.caffeine.R
import com.thechance.caffeine.presentation.feature.home.composable.topBar.CaffeineTopBar
import com.thechance.caffeine.presentation.feature.home.composable.topBar.TopBarIcon

@Composable
fun CoffeeIsReadyScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CaffeineTopBar(
                leadingIcon = {
                    TopBarIcon(
                        icon = painterResource(R.drawable.ic_cancel),
                        contentDescription = "cancel icon",
                        onIconClick = {}
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
                    .background(color = Color(0xFF7C351B))
                    .clip(CircleShape)
            )

            Text(
                text = "Your coffee is ready,\n" + "Enjoy",
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
    }
}