package ua.smartwaste.kmp.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

@Composable
fun SmartButton(
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = SmartTheme.palette.primary,
        contentColor = SmartTheme.palette.onPrimary,
        disabledBackgroundColor = SmartTheme.palette.primary.copy(
            alpha = 0.2f,
        ),
    ),
    text: String = "",
    textStyle: TextStyle = SmartTheme.typography.bodyMedium,
    content: @Composable RowScope.() -> Unit = {
        Text(
            text = text,
            color = SmartTheme.palette.onPrimary,
            style = textStyle,
        )
    },
    shape: RoundedCornerShape = SmartTheme.shape.medium,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = buttonColors,
        content = content,
        shape = shape,
        enabled = enabled,
    )
}
