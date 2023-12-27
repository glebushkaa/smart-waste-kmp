package ua.smartwaste.kmp.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import ua.smartwaste.kmp.presentation.components.AnimatedTopBar
import ua.smartwaste.kmp.presentation.screens.login.LoginScreen
import ua.smartwaste.kmp.presentation.screens.splash.SplashScreen
import ua.smartwaste.kmp.presentation.theme.SmartTheme

@Composable
fun App() {
    SmartTheme {
        Navigator(SplashScreen()) { navigator ->
            val visible by remember {
                derivedStateOf {
                    navigator.lastItem !is SplashScreen && navigator.lastItem !is LoginScreen
                }
            }
            Scaffold(
                topBar = {
                    AnimatedTopBar(visible = visible)
                },
                content = {
                    navigator.lastItem.Content()
                },
                bottomBar = { },
                backgroundColor = SmartTheme.palette.background,
            )
        }
    }
}
