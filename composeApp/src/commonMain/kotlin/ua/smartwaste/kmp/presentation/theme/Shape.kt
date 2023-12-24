package ua.smartwaste.kmp.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/24/2023
 */

@Immutable
data class Shape(
    val tiny: RoundedCornerShape,
    val small: RoundedCornerShape,
    val medium: RoundedCornerShape,
    val large: RoundedCornerShape,
    val huge: RoundedCornerShape,
    val gigantic: RoundedCornerShape,
    val round: RoundedCornerShape,
)

val shape = Shape(
    tiny = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    huge = RoundedCornerShape(20.dp),
    gigantic = RoundedCornerShape(24.dp),
    round = RoundedCornerShape(360.dp),
)
