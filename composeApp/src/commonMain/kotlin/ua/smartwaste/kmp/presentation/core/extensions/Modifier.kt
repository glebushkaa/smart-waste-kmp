package ua.smartwaste.kmp.presentation.core.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

fun Modifier.applyIf(
    condition: Boolean,
    modifier: @Composable Modifier.() -> Modifier,
) = composed { if (condition) then(modifier()) else this }

fun Modifier.clickableWithoutRipple(
    onClick: () -> Unit,
): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick,
    )
}
