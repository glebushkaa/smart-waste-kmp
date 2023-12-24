package ua.smartwaste.kmp.presentation.screens.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.coroutines.delay
import ua.smartwaste.kmp.presentation.core.EIGHT_HUNDRED_MILLIS
import ua.smartwaste.kmp.presentation.core.painterDrawableResource
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/24/2023
 */

@Composable
fun SplashScreen() {
    var logoAlpha by remember { mutableFloatStateOf(LOGO_START_ALPHA) }
    val animatedLogoAlpha by animateFloatAsState(
        animationSpec = tween(EIGHT_HUNDRED_MILLIS.toInt()),
        targetValue = logoAlpha,
        label = "",
    )
    var logoScale by remember { mutableFloatStateOf(LOGO_START_SCALE) }
    val animatedLogoScale by animateFloatAsState(
        animationSpec = tween(EIGHT_HUNDRED_MILLIS.toInt()),
        targetValue = logoScale,
        label = "",
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SmartTheme.palette.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .size(SmartTheme.dimension.splash.logo)
                .graphicsLayer {
                    alpha = animatedLogoAlpha
                    scaleX = animatedLogoScale
                    scaleY = animatedLogoScale
                },
            painter = painterDrawableResource("img_recycle"),
            contentDescription = null,
        )
        Text(
            modifier = Modifier.graphicsLayer {
                alpha = animatedLogoAlpha
                scaleX = animatedLogoScale
                scaleY = animatedLogoScale
            },
            text = "Smart Waste",
            style = SmartTheme.typography.headlineMedium,
            color = SmartTheme.palette.onBackground,
        )
    }

    LaunchedEffect(key1 = Unit) {
        logoAlpha = LOGO_END_ALPHA
        logoScale = LOGO_END_SCALE
    }
}

private const val LOGO_START_SCALE = 0.3f
private const val LOGO_END_SCALE = 1f

private const val LOGO_START_ALPHA = 0f
private const val LOGO_END_ALPHA = 1f
