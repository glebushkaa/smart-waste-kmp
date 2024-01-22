package ua.smartwaste.kmp.presentation.screens.bucket

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.AwtWindow
import androidx.compose.ui.window.rememberWindowState
import ua.smartwaste.kmp.presentation.core.SideEffectHandler
import java.awt.FileDialog
import java.awt.FileDialog.LOAD
import java.awt.Frame

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 1/2/2024
 */

@Composable
actual fun BucketScreen(
    state: BucketState,
    sideEffect: BucketSideEffect?,
    sendEvent: (BucketEvent) -> Unit
) {
    var isFileChooserOpen by remember { mutableStateOf(false) }

    BucketScreenContent(
        state = state,
        sendEvent = sendEvent
    )

    if (isFileChooserOpen) {
        FileChooserDialog(
            onCloseRequest = {
                isFileChooserOpen = false
                println("Result $it")
            }
        )
    }

    SideEffectHandler(
        sideEffect = sideEffect,
        action = { effect ->
            when (effect) {
                BucketSideEffect.ScanRubbish -> isFileChooserOpen = true
                is BucketSideEffect.Toast -> {}
            }
        },
        clearEffect = {
            sendEvent(BucketEvent.ClearSideEffect)
        }
    )
}

@Composable
private fun FileChooserDialog(
    parent: Frame? = null,
    onCloseRequest: (result: String?) -> Unit
) {
    AwtWindow(
        create = {
            FileDialog(parent, "Choose a file", LOAD).apply {
                file = "*.jpg"
            }
        },
        dispose = FileDialog::dispose
    )
}