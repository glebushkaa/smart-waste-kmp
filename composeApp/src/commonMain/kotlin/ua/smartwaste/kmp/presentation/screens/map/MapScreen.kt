package ua.smartwaste.kmp.presentation.screens.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import ua.smartwaste.kmp.presentation.screens.splash.SplashScreen
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

class MapScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        MapScreenContent {
            navigator?.replaceAll(SplashScreen())
        }
    }
}

@Composable
private fun MapScreenContent(
    rerun: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SmartTheme.palette.background),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "MapScreen",
        )
        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = rerun,
        ) { Text(text = "Rerun") }
    }
}
