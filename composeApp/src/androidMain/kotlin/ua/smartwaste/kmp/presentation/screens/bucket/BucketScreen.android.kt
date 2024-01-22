package ua.smartwaste.kmp.presentation.screens.bucket

import android.Manifest.permission.CAMERA
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import ua.smartwaste.kmp.core.extensions.checkPermission
import ua.smartwaste.kmp.core.extensions.toast
import ua.smartwaste.kmp.presentation.core.SideEffectHandler
import java.io.File
import java.util.UUID

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 1/2/2024
 */

@Composable
actual fun BucketScreen(
    state: BucketState,
    sideEffect: BucketSideEffect?,
    sendEvent: (BucketEvent) -> Unit
) {
    val context = LocalContext.current
    var uri by remember { mutableStateOf<Uri?>(null) }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
    ) { pair ->
        val path = uri?.toString()
        if (pair && path != null) {
            val event = BucketEvent.ProcessScannedItemPath(path)
            sendEvent(event)
            return@rememberLauncherForActivityResult
        }
        val event = BucketEvent.SendToast("Image was not saved")
        sendEvent(event)
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (!isGranted) {
            val event = BucketEvent.SendToast("Camera permission is required to use this feature")
            sendEvent(event)
            return@rememberLauncherForActivityResult
        }
        uri = context.getTmpFileUri()
        cameraLauncher.launch(uri)
    }

    BucketScreenContent(
        state = state,
        sendEvent = sendEvent
    )

    SideEffectHandler(
        sideEffect = sideEffect,
        action = { effect ->
            when (effect) {
                BucketSideEffect.ScanRubbish -> {
                    if (!context.checkPermission(CAMERA)) {
                        cameraPermissionLauncher.launch(CAMERA)
                        return@SideEffectHandler
                    }
                    uri = context.getTmpFileUri()
                    cameraLauncher.launch(uri)
                }

                is BucketSideEffect.Toast -> context.toast(effect.message)
            }
        },
        clearEffect = {
            sendEvent(BucketEvent.ClearSideEffect)
        }
    )
}

private fun Context.getTmpFileUri(): Uri {
    val uuid = UUID.randomUUID().toString()
    val tmpFile = File(filesDir, "$uuid.jpg")

    return FileProvider.getUriForFile(
        applicationContext,
        "${applicationContext.packageName}.provider",
        tmpFile,
    )
}