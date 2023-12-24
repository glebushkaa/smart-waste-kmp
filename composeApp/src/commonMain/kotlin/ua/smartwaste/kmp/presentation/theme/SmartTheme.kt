@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package ua.smartwaste.kmp.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import ua.smartwaste.kmp.presentation.theme.SmartTheme.typography

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/24/2023
 */

val LocalDimensions = staticCompositionLocalOf { Dimension() }
val LocalTypography = staticCompositionLocalOf { Typography() }
val LocalPalette = staticCompositionLocalOf { Palette() }
val LocalOffset = staticCompositionLocalOf { SizedOffset() }
val LocalShapes = staticCompositionLocalOf { shape }

@Composable
fun SmartTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val palette = if (darkTheme) darkPalette else lightPalette
    val windowSize = calculateWindowSizeClass()
    val dimension = buildDimension(windowSize)
    val offset = buildSizedOffset(windowSize)

    CompositionLocalProvider(
        LocalDimensions provides dimension,
        LocalPalette provides palette,
        LocalTypography provides typography,
        LocalOffset provides offset,
        content = content,
    )
}

object SmartTheme {

    val palette: Palette
        @Composable
        get() = LocalPalette.current

    val offset: SizedOffset
        @Composable
        get() = LocalOffset.current

    val shape: Shape
        @Composable
        get() = LocalShapes.current

    val typography: Typography
        @Composable
        get() = LocalTypography.current

    val dimension: Dimension
        @Composable
        get() = LocalDimensions.current
}
