package ua.smartwaste.kmp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

@Composable
fun SmartOutlinedIconButton(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    painterResource: Painter,
    iconTint: Color = SmartTheme.palette.onSurface,
    border: BorderStroke = BorderStroke(
        width = 1.dp,
        color = SmartTheme.palette.onBackground.copy(alpha = 0.5f),
    ),
    contentPadding: PaddingValues = PaddingValues(
        SmartTheme.offset.width.default,
    ),
    shape: RoundedCornerShape = SmartTheme.shape.small,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        border = border,
        contentPadding = contentPadding,
        shape = shape,
        enabled = enabled,
    ) {
        Icon(
            modifier = iconModifier,
            painter = painterResource,
            contentDescription = null,
            tint = if (enabled) iconTint else iconTint.copy(alpha = 0.2f),
        )
    }
}
