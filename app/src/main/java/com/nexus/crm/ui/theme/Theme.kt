package com.nexus.crm.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = SupabaseGreen,
    onPrimary = NearBlack,
    primaryContainer = SupabaseGreen,
    onPrimaryContainer = NearBlack,
    secondary = GreenLink,
    onSecondary = NearBlack,
    secondaryContainer = GreenBorder,
    onSecondaryContainer = SupabaseGreen,
    tertiary = StatusOpportunity,
    onTertiary = NearBlack,
    tertiaryContainer = MidBorder,
    onTertiaryContainer = OffWhite,
    error = PriorityHigh,
    onError = OffWhite,
    errorContainer = MidBorder,
    onErrorContainer = PriorityHigh,
    background = DarkBackground,
    onBackground = OffWhite,
    surface = DarkBackground,
    onSurface = OffWhite,
    surfaceVariant = NearBlack,
    onSurfaceVariant = LightGray,
    outline = BorderDark,
    outlineVariant = DarkBorder,
    inverseSurface = OffWhite,
    inverseOnSurface = DarkBackground,
    inversePrimary = SupabaseGreen,
    surfaceTint = SupabaseGreen
)

@Composable
fun NexusTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = DarkBackground.toArgb()
            window.navigationBarColor = NearBlack.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}