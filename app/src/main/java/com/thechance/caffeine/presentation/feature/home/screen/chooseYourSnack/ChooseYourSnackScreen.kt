package com.thechance.caffeine.presentation.feature.home.screen.chooseYourSnack

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thechance.caffeine.R
import com.thechance.caffeine.presentation.feature.home.composable.custom.SnacksList
import com.thechance.caffeine.presentation.feature.home.composable.modifier.topBarModifier
import com.thechance.caffeine.presentation.feature.home.composable.topBar.CaffeineTopBar
import com.thechance.caffeine.presentation.feature.home.composable.topBar.TopBarIcon
import com.thechance.caffeine.presentation.feature.home.event.OnClick
import com.thechance.caffeine.presentation.feature.home.model.HomeScreenState
import com.thechance.caffeine.presentation.theme.CaffeineTheme
import com.thechance.caffeine.presentation.theme.TextColorLight

@Composable
fun ChooseYourSnackScreen(
    chooseYourSnackUiState: HomeScreenState.ChooseYourSnackUiState,
    onSelectSnack: (Int) -> Unit,
    onCancel: OnClick,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier) {
        Column(
            Modifier
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

            Text(
                text = "Take your snack",
                color = TextColorLight,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 22.sp,
                    lineHeight = 22.sp
                ),
                modifier = Modifier.padding(
                    vertical = 24.dp,
                    horizontal = 16.dp
                )
            )
            SnacksList(
                modifier = Modifier.fillMaxWidth(),
                list = chooseYourSnackUiState.snackList,
                onClick = onSelectSnack
            )
        }
    }
}

@Preview
@Composable
fun ChooseYourSnakePreview(modifier: Modifier = Modifier) {
    CaffeineTheme {
        ChooseYourSnackScreen(
            onSelectSnack = {},
            onCancel = {},
            chooseYourSnackUiState = HomeScreenState.ChooseYourSnackUiState(
                snackList = listOf(
                    HomeScreenState.ChooseYourSnackUiState.SnackUi(
                        title = "chocolate",
                        image = R.drawable.im_chocolate
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
                        title = "Oreo",
                        image = R.drawable.im_oreo
                    )
                )
            )
        )
    }
}