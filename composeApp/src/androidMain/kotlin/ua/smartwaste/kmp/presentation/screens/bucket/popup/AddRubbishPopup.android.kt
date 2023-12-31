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
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch
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
    addClicked: (Long, Int) -> Unit,
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        modifier = modifier.defaultMinSize(minHeight = 400.dp),
        onDismissRequest = dismissRequest,
        containerColor = SmartTheme.palette.surface,
        sheetState = modalBottomSheetState,
    ) {
        AddRubbishPopupContent(
            availableRubbishes = availableRubbishes,
            cancelClicked = {
                scope.launch {
                    modalBottomSheetState.hide()
                    dismissRequest()
                }
            },
            addClicked = { id, count ->
                scope.launch {
                    addClicked(id, count)
                    modalBottomSheetState.hide()
                    dismissRequest()
                }
            },
            scanClicked = {
            },
        )
    }
}
