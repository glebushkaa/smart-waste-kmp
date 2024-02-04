package ua.smartwaste.kmp.presentation.screens.bucket

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import ua.smartwaste.kmp.presentation.components.SmartButton
import ua.smartwaste.kmp.presentation.components.SmartOutlinedButton
import ua.smartwaste.kmp.presentation.popup.SmartLoader
import ua.smartwaste.kmp.presentation.screens.bucket.components.RubbishList
import ua.smartwaste.kmp.presentation.screens.bucket.popup.AddRubbishPopup
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

object BucketScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<BucketScreenModel>()
        val state by screenModel.state.collectAsState()
        val sideEffect by screenModel.sideEffect.collectAsState(null)

        if (state.loaderVisible) {
            SmartLoader()
        }

        if (state.rubbishPopupState.visible) {
            AddRubbishPopup(
                rubbishPopupState = state.rubbishPopupState,
                sendEvent = screenModel::sendEvent,
            )
        }

        BucketScreen(
            state = state,
            sideEffect = sideEffect,
            sendEvent = screenModel::sendEvent,
        )
    }
}

@Composable
expect fun BucketScreen(
    state: BucketState,
    sideEffect: BucketSideEffect?,
    sendEvent: (BucketEvent) -> Unit,
)

@Composable
fun BucketScreenContent(
    state: BucketState = BucketState(),
    sendEvent: (BucketEvent) -> Unit = {},
) {
    val dumpButtonEnabled by remember {
        derivedStateOf { state.selectedRubbishes.isNotEmpty() }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        RubbishList(
            modifier = Modifier
                .padding(
                    top = SmartTheme.offset.height.average,
                    start = SmartTheme.offset.width.huge,
                    end = SmartTheme.offset.width.huge,
                )
                .weight(1f),
            rubbishList = state.selectedRubbishes,
            increaseClicked = { id ->
                val event = BucketEvent.IncreaseCount(id)
                sendEvent(event)
            },
            decreaseClicked = { id ->
                val event = BucketEvent.DecreaseCount(id)
                sendEvent(event)
            }
        )
        SmartOutlinedButton(
            modifier = Modifier
                .padding(
                    vertical = SmartTheme.offset.height.regular,
                    horizontal = SmartTheme.offset.width.huge,
                )
                .height(
                    SmartTheme.dimension.bucket.addButtonHeight
                )
                .fillMaxWidth(),
            border = BorderStroke(
                width = 2.dp,
                color = SmartTheme.palette.primary,
            ),
            shape = SmartTheme.shape.medium,
            text = "Add item",
            textStyle = SmartTheme.typography.bodyLarge,
            onClick = {
                sendEvent(BucketEvent.ShowAddRubbishPopup)
            },
        )
        SmartButton(
            modifier = Modifier
                .padding(
                    bottom = SmartTheme.offset.height.huge,
                    start = SmartTheme.offset.width.huge,
                    end = SmartTheme.offset.width.huge,
                )
                .height(
                    SmartTheme.dimension.bucket.recyclerPointsButtonHeight
                )
                .fillMaxWidth(),
            text = "Show recycle points",
            textStyle = SmartTheme.typography.bodyLarge,
            enabled = dumpButtonEnabled,
        ) {
//            sendEvent(BucketEvent.OpenMapClicked)
        }
    }
}
