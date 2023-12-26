package ua.smartwaste.kmp.presentation.screens.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import kotlinx.coroutines.delay
import ua.smartwaste.kmp.presentation.core.EIGHT_HUNDRED_MILLIS
import ua.smartwaste.kmp.presentation.core.FIVE_HUNDRED_MILLIS
import ua.smartwaste.kmp.presentation.core.painterDrawableResource
import ua.smartwaste.kmp.presentation.screens.login.LoginScreen
import ua.smartwaste.kmp.presentation.screens.profile.ProfileScreen
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/24/2023
 */

class SplashScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<SplashScreenModel>()
        val navigationEvent by screenModel.navigationEvent.collectAsState(null)
        val navigator = LocalNavigator.current

        SplashScreenContent(
            sendEvent = screenModel::sendEvent,
        )

        LaunchedEffect(key1 = navigationEvent) {
            val event = navigationEvent ?: return@LaunchedEffect
            when (event) {
                SplashNavigationEvent.NavigateToLogin -> {
                    navigator?.replaceAll(LoginScreen())
                }

                SplashNavigationEvent.NavigateToProfile -> {
                    navigator?.replaceAll(ProfileScreen)
                }
            }
        }
    }
}

@Composable
private fun SplashScreenContent(
    sendEvent: (SplashEvent) -> Unit = {},
) {
    var logoVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SmartTheme.palette.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimatedVisibility(
            visible = logoVisible,
            enter = fadeIn(
                animationSpec = tween(EIGHT_HUNDRED_MILLIS.toInt()),
                initialAlpha = LOGO_START_ALPHA,
            ) + scaleIn(
                animationSpec = tween(FIVE_HUNDRED_MILLIS.toInt()),
                initialScale = LOGO_START_SCALE,
            ),
        ) {
            Image(
                modifier = Modifier.size(SmartTheme.dimension.splash.logo),
                painter = painterDrawableResource("img_recycle"),
                contentDescription = null,
            )
        }
        AnimatedVisibility(
            visible = logoVisible,
            enter = fadeIn(
                animationSpec = tween(EIGHT_HUNDRED_MILLIS.toInt()),
                initialAlpha = LOGO_START_ALPHA,
            ) + scaleIn(
                animationSpec = tween(FIVE_HUNDRED_MILLIS.toInt()),
                initialScale = LOGO_START_SCALE,
            ),
        ) {
            Text(
                text = "Smart Waste",
                style = SmartTheme.typography.headlineMedium,
                color = SmartTheme.palette.onBackground,
            )
        }
    }

    LaunchedEffect(Unit) {
        delay(EIGHT_HUNDRED_MILLIS)
        logoVisible = true
        sendEvent(SplashEvent.ValidateToken)
    }
}

private const val LOGO_START_SCALE = 0.3f
private const val LOGO_START_ALPHA = 0f
