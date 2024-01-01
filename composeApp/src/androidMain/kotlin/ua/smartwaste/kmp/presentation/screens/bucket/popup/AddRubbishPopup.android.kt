@file:OptIn(ExperimentalMaterial3Api::class)

package ua.smartwaste.kmp.presentation.screens.bucket.popup

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ua.smartwaste.kmp.presentation.screens.bucket.BucketEvent
import ua.smartwaste.kmp.presentation.screens.bucket.BucketState
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

@Composable
actual fun AddRubbishPopup(
    modifier: Modifier,
    rubbishPopupState: BucketState.RubbishPopupState,
    sendEvent: (BucketEvent) -> Unit,
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        modifier = modifier.defaultMinSize(minHeight = 400.dp),
        onDismissRequest = {
            sendEvent(BucketEvent.HideRubbishPopup)
        },
        containerColor = SmartTheme.palette.surface,
        sheetState = modalBottomSheetState,
    ) {
        AddRubbishPopupContent(
            rubbishPopupState = rubbishPopupState,
            cancelClicked = {
                scope.launch {
                    modalBottomSheetState.hide()
                    sendEvent(BucketEvent.HideRubbishPopup)
                }
            },
            addClicked = {
                scope.launch {
                    sendEvent(BucketEvent.AddRubbish)
                    modalBottomSheetState.hide()
                    sendEvent(BucketEvent.HideRubbishPopup)
                }
            },
            scanClicked = { sendEvent(BucketEvent.ScanClicked) },
            selectRubbish = { id ->
                val event = BucketEvent.SelectRubbish(id)
                sendEvent(event)
            },
            modeChange = { mode ->
                val event = BucketEvent.ChangeRubbishPopupMode(mode)
                sendEvent(event)
            },
            increaseCount = {
                sendEvent(BucketEvent.IncreaseRubbishPopupCount)
            },
            decreaseCount = {
                sendEvent(BucketEvent.DecreaseRubbishPopupCount)
            }
        )
    }
}
