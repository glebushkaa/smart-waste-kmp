package ua.smartwaste.kmp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

@Composable
fun SmartOutlinedButton(
    modifier: Modifier = Modifier,
    border: BorderStroke = BorderStroke(
        width = 2.dp,
        color = SmartTheme.palette.onSurface.copy(alpha = 0.5f),
    ),
    shape: RoundedCornerShape = SmartTheme.shape.small,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(
        SmartTheme.offset.width.default,
    ),
    text: String = "",
    textStyle: TextStyle = SmartTheme.typography.bodyMedium,
    content: @Composable RowScope.() -> Unit = {
        Text(
            text = text,
            color = SmartTheme.palette.primary,
            style = textStyle,
        )
    },
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(
        containerColor = Color.Transparent,
        contentColor = SmartTheme.palette.primary,
    ),
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        border = border,
        shape = shape,
        enabled = enabled,
        content = content,
        contentPadding = contentPadding,
        colors = colors,
    )
}
