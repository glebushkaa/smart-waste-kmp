package ua.smartwaste.kmp.presentation.popup

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import ua.smartwaste.kmp.presentation.core.ONE_SECOND
import ua.smartwaste.kmp.presentation.core.painterDrawableResource

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

@Composable
fun SmartLoader() {
    Popup(
        properties = PopupProperties(focusable = true),
        onDismissRequest = {},
    ) {
        val infiniteTransition = rememberInfiniteTransition(label = "")
        val angle by infiniteTransition.animateFloat(
            initialValue = START_ANGLE,
            targetValue = CIRCLE_ANGLE,
            animationSpec = infiniteRepeatable(
                tween(ONE_SECOND.toInt()),
                RepeatMode.Restart,
            ),
            label = "",
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.7f)),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                modifier = Modifier.rotate(angle),
                painter = painterDrawableResource("img_recycle"),
                contentDescription = null,
            )
        }
    }
}

private const val START_ANGLE = 0f
private const val CIRCLE_ANGLE = 360f
