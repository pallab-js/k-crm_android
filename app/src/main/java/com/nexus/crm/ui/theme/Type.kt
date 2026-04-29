package com.nexus.crm.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val FontFamilyPrimary = FontFamily.SansSerif
val FontFamilyMono = FontFamily.Monospace

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamilyPrimary,
        fontSize = 72.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 72.sp,
        letterSpacing = 0.sp
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamilyPrimary,
        fontSize = 36.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 45.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamilyPrimary,
        fontSize = 24.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 32.sp,
        letterSpacing = (-0.16).sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamilyPrimary,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamilyMono,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
        letterSpacing = 1.2.sp
    )
)