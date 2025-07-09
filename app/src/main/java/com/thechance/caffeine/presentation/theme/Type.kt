package com.thechance.caffeine.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.thechance.caffeine.R

val SingletFontFamily = FontFamily(
    listOf(Font(resId = R.font.font_sniglet_regular, weight = FontWeight.Normal))
)

val urbanistFontFamily = FontFamily(
    listOf(
        Font(resId = R.font.font_urbanist_bold, weight = FontWeight.Bold),
        Font(resId = R.font.font_urbanist_medium, weight = FontWeight.Normal)
    )
)

val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = urbanistFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 10.sp,
        letterSpacing = 0.25.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = urbanistFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.25.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = urbanistFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.25.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = urbanistFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.25.sp
    ),
    titleLarge = TextStyle(
        fontFamily = urbanistFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.25.sp
    ),
    displayLarge = TextStyle(
        fontFamily = SingletFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 50.sp,
        letterSpacing = 0.25.sp,
        color = TextColorLight
    )
)