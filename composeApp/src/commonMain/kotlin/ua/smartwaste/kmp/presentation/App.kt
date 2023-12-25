package ua.smartwaste.kmp.presentation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import ua.smartwaste.kmp.presentation.screens.splash.SplashScreen

@Composable
fun App() {
    Navigator(
        screen = SplashScreen(),
    )
}
