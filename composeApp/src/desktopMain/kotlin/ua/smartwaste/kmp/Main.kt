package ua.smartwaste.kmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import ua.smartwaste.kmp.presentation.App
import ua.smartwaste.kmp.presentation.core.painterDrawableResource

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/24/2023
 */

fun main() = application {
    Window(
        title = "SmartWaste",
        icon = painterDrawableResource("img_recycle"),
        onCloseRequest = ::exitApplication,
        state = WindowState(
            placement = WindowPlacement.Fullscreen,
        ),
    ) {
        App()
    }
}
