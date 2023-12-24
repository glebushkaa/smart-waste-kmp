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
data class SizedOffset(
    val width: Offset = buildOffset(),
    val height: Offset = buildOffset(),
)

@Immutable
data class Offset(
    val default: Dp,
    val min: Dp,
    val tiny: Dp,
    val small: Dp,
    val medium: Dp,
    val regular: Dp,
    val average: Dp,
    val large: Dp,
    val huge: Dp,
    val gigantic: Dp,
    val superGigantic: Dp,
    val ultraGigantic: Dp,
)

fun buildSizedOffset(
    sizeClass: WindowSizeClass,
): SizedOffset {
    val heightMultiplier = when (sizeClass.heightSizeClass) {
        WindowHeightSizeClass.Compact -> 1f
        WindowHeightSizeClass.Medium -> 1.3f
        WindowHeightSizeClass.Expanded -> 1.6f
        else -> 1f
    }
    val widthMultiplier = when (sizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 1f
        WindowWidthSizeClass.Medium -> 1.3f
        WindowWidthSizeClass.Expanded -> 1.6f
        else -> 1f
    }
    return SizedOffset(
        height = buildOffset(heightMultiplier),
        width = buildOffset(widthMultiplier),
    )
}

private fun buildOffset(multiplier: Float = 1f): Offset {
    return Offset(
        default = (0 * multiplier).dp,
        min = (2 * multiplier).dp,
        tiny = (4 * multiplier).dp,
        small = (8 * multiplier).dp,
        medium = (12 * multiplier).dp,
        regular = (16 * multiplier).dp,
        average = (20 * multiplier).dp,
        large = (24 * multiplier).dp,
        huge = (32 * multiplier).dp,
        gigantic = (48 * multiplier).dp,
        superGigantic = (64 * multiplier).dp,
        ultraGigantic = (84 * multiplier).dp,
    )
}
