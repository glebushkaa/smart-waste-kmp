package ua.smartwaste.kmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
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
    Napier.base(DebugAntilog())
    application {
        val minSize = Dimension(400, 800)

        Window(
            title = "SmartWaste",
            icon = painterDrawableResource("img_recycle"),
            onCloseRequest = ::exitApplication,
            state = WindowState(),
        ) {
            window.minimumSize = minSize
            App()
        }
    }
}
