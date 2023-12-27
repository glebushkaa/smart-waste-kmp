package ua.smartwaste.kmp.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

@Composable
fun SmartIconButton(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    painterResource: Painter,
    tint: Color = SmartTheme.palette.onBackground,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            modifier = iconModifier,
            painter = painterResource,
            contentDescription = null,
            tint = tint,
        )
    }
}
