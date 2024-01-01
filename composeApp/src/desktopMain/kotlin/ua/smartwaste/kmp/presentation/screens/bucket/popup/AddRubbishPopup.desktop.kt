package ua.smartwaste.kmp.presentation.screens.bucket.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch
import ua.smartwaste.kmp.model.Rubbish
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
    Dialog(
        onDismissRequest = {
            sendEvent(BucketEvent.HideRubbishPopup)
        },
        properties = DialogProperties(),
        content = {
            AddRubbishPopupContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = SmartTheme.palette.surface,
                        shape = SmartTheme.shape.large,
                    )
                    .padding(
                        start = SmartTheme.offset.width.small,
                        end = SmartTheme.offset.width.small,
                        top = SmartTheme.offset.width.regular,
                    ),
                rubbishPopupState = rubbishPopupState,
                cancelClicked = {
                    sendEvent(BucketEvent.HideRubbishPopup)
                },
                addClicked = {
                    sendEvent(BucketEvent.AddRubbish)
                    sendEvent(BucketEvent.HideRubbishPopup)
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
        },
    )
}
