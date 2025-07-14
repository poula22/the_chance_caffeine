package com.thechance.caffeine.presentation.feature.home.screen.thankYou

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.caffeine.R
import com.thechance.caffeine.presentation.feature.home.composable.button.CaffeineButton
import com.thechance.caffeine.presentation.feature.home.composable.modifier.topBarModifier
import com.thechance.caffeine.presentation.feature.home.composable.topBar.CaffeineTopBar
import com.thechance.caffeine.presentation.feature.home.composable.topBar.TopBarIcon
import com.thechance.caffeine.presentation.feature.home.event.OnClick
import com.thechance.caffeine.presentation.theme.CaffeineTheme
import com.thechance.caffeine.presentation.theme.SingletFontFamily
import com.thechance.caffeine.presentation.theme.TextColorLight

@Composable
fun ThankYouScreen(
    snackImagePainter: Painter,
    onCancel: OnClick,
    onThankYouClicked: OnClick,
    modifier: Modifier = Modifier
) {
    val color = Color(0xFF7C351B)

    Scaffold(modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            CaffeineTopBar(
                modifier = Modifier.topBarModifier(),
                leadingIcon = {
                    TopBarIcon(
                        icon = painterResource(R.drawable.ic_cancel),
                        contentDescription = "cancel icon",
                        onIconClick = onCancel
                    )
                }
            )

            Row(Modifier.padding(vertical = 24.dp)) {
                Icon(
                    painter = painterResource(R.drawable.ic_coffee_beans),
                    contentDescription = "snack image",
                    tint = color,
                    modifier = Modifier.size(32.dp)
                )

                Text(
                    text = "More Espresso, Less Depresso",
                    color = color,
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    letterSpacing = 0.25.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = SingletFontFamily,
                    modifier = Modifier
                        .padding(vertical = 6.dp)
                        .align(Alignment.CenterVertically)
                )

                Icon(
                    painter = painterResource(R.drawable.ic_coffee_beans),
                    contentDescription = "snack image",
                    tint = color,
                    modifier = Modifier.size(32.dp)
                )
            }

            Image(
                painter = snackImagePainter,
                contentDescription = "snack image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            )

            Row(Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "Bon appétit",
                    color = TextColorLight.copy(0.8f),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 22.sp,
                        lineHeight = 22.sp
                    )
                )

                Icon(
                    painter = painterResource(R.drawable.magic_wand),
                    contentDescription = "Bon appétit",
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(Modifier.weight(1f))

            CaffeineButton(
                text = "Thank youuu",
                iconPainter = painterResource(R.drawable.ic_arrow_continue),
                onClick = onThankYouClicked,
                modifier = Modifier.padding(bottom = 50.dp)
            )
        }
    }
}


@Preview
@Composable
private fun ThankYouScreenPreview() {
    CaffeineTheme {
        ThankYouScreen(
            snackImagePainter = painterResource(R.drawable.im_oreo),
            onCancel = {},
            onThankYouClicked = {}
        )
    }
}