package ua.smartwaste.kmp.presentation.screens.bucket.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.collections.immutable.ImmutableList
import ua.smartwaste.kmp.model.Rubbish
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

@Composable
actual fun AddRubbishPopup(
    modifier: Modifier,
    availableRubbishes: ImmutableList<Rubbish>,
    dismissRequest: () -> Unit,
) {
    Dialog(
        onDismissRequest = dismissRequest,
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
                        start = SmartTheme.offset.width.large,
                        end = SmartTheme.offset.width.large,
                        top = SmartTheme.offset.width.huge,
                    ),
                availableRubbishes = availableRubbishes,
                cancelClicked = dismissRequest,
                addClicked = { dismissRequest() },
                scanClicked = {},
            )
        },
    )
}
