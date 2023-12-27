package ua.smartwaste.kmp.presentation

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import org.koin.compose.koinInject
import ua.smartwaste.kmp.presentation.components.AnimatedTopBar
import ua.smartwaste.kmp.presentation.core.FOUR_HUNDRED_MILLIS
import ua.smartwaste.kmp.presentation.screens.login.LoginScreen
import ua.smartwaste.kmp.presentation.screens.profile.ProfileScreen
import ua.smartwaste.kmp.presentation.screens.splash.SplashScreen
import ua.smartwaste.kmp.presentation.tabs.AnimatedBottomTabs
import ua.smartwaste.kmp.presentation.theme.SmartTheme

@Composable
fun App() {
    val mainScreenModel = koinInject<MainScreenModel>()

    SmartTheme {
        Navigator(SplashScreen()) { navigator ->
            val visible by remember {
                derivedStateOf {
                    navigator.lastItem !is SplashScreen &&
                        navigator.lastItem !is LoginScreen
                }
            }
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    AnimatedTopBar(
                        visible = visible,
                        logOut = {
                            mainScreenModel.logOut()
                            navigator.replaceAll(LoginScreen)
                        },
                        logOutVisible = navigator.lastItem is ProfileScreen,
                    )
                },
                content = {
                    FadeTransition(
                        modifier = Modifier.padding(it),
                        animationSpec = tween(FOUR_HUNDRED_MILLIS.toInt()),
                        navigator = navigator,
                    ) { screen ->
                        screen.Content()
                    }
                },
                bottomBar = {
                    AnimatedBottomTabs(
                        navigator = navigator,
                        visible = visible,
                    )
                },
                backgroundColor = SmartTheme.palette.background,
            )
        }
    }
}
