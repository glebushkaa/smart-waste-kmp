package ua.smartwaste.kmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import org.koin.core.context.startKoin
import ua.smartwaste.kmp.di.modules
import ua.smartwaste.kmp.presentation.App
import ua.smartwaste.kmp.presentation.core.painterDrawableResource
import java.awt.Dimension

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/24/2023
 */

fun main() {
    startKoin { modules(modules = modules) }
    application {
        val minSize = Dimension(400, 800)

        Window(
            title = "SmartWaste",
            icon = painterDrawableResource("img_recycle"),
            onCloseRequest = ::exitApplication,
            state = WindowState(
                placement = WindowPlacement.Fullscreen,
            ),
        ) {
            window.minimumSize = minSize
            App()
        }
    }
}
