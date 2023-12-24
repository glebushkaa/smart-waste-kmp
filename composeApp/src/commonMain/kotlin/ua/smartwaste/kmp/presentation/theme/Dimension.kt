package ua.smartwaste.kmp.presentation.theme

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/24/2023
 */

@Immutable
data class Dimension(
    val splash: SplashDimension = SplashDimension(),
) {
    @Immutable
    data class SplashDimension(val logo: Dp = 60.dp)
}

fun buildDimension(
    windowSizeClass: WindowSizeClass,
): Dimension {
    return Dimension(
        splash = buildSplashDimension(windowSizeClass),
    )
}

private fun buildSplashDimension(
    windowSizeClass: WindowSizeClass,
): Dimension.SplashDimension {
    return Dimension.SplashDimension(
        logo = buildGeneralDimension(60.dp.value, windowSizeClass),
    )
}

private fun buildWidthDimension(
    compactSize: Float,
    sizeClass: WindowWidthSizeClass,
): Dp {
    return when (sizeClass) {
        WindowWidthSizeClass.Compact -> compactSize
        WindowWidthSizeClass.Medium -> compactSize * 1.3f
        WindowWidthSizeClass.Expanded -> compactSize * 1.6f
        else -> compactSize
    }.dp
}

private fun buildHeightDimension(
    compactSize: Float,
    sizeClass: WindowHeightSizeClass,
): Dp {
    return when (sizeClass) {
        WindowHeightSizeClass.Compact -> compactSize
        WindowHeightSizeClass.Medium -> compactSize * 1.3f
        WindowHeightSizeClass.Expanded -> compactSize * 1.6f
        else -> compactSize
    }.dp
}

private fun buildGeneralDimension(
    compactWidth: Float,
    sizeClass: WindowSizeClass,
): Dp {
    val heightSize = buildHeightDimension(compactWidth, sizeClass.heightSizeClass)
    val widthSize = buildWidthDimension(compactWidth, sizeClass.widthSizeClass)
    return minOf(heightSize, widthSize)
}
