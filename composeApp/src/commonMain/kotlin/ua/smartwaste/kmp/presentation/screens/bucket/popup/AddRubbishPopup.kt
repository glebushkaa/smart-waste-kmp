package ua.smartwaste.kmp.presentation.screens.bucket.popup

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ua.smartwaste.kmp.presentation.screens.bucket.BucketEvent
import ua.smartwaste.kmp.presentation.screens.bucket.BucketState
import ua.smartwaste.kmp.presentation.screens.bucket.RubbishPopupMode
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

@Composable
expect fun AddRubbishPopup(
    modifier: Modifier = Modifier,
    rubbishPopupState: BucketState.RubbishPopupState,
    sendEvent: (BucketEvent) -> Unit,
)

@Composable
fun AddRubbishPopupContent(
    modifier: Modifier = Modifier,
    rubbishPopupState: BucketState.RubbishPopupState,
    addClicked: () -> Unit,
    cancelClicked: () -> Unit,
    modeChange: (RubbishPopupMode) -> Unit,
    selectRubbish: (Long) -> Unit,
    scanClicked: () -> Unit,
    increaseCount: () -> Unit,
    decreaseCount: () -> Unit
) {
    AddRubbishAnimatedContent(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = SmartTheme.palette.surface,
                shape = SmartTheme.shape.large,
            )
            .padding(horizontal = SmartTheme.offset.width.huge),
        mode = rubbishPopupState.mode,
    ) {
        when (it) {
            RubbishPopupMode.ADD -> {
                AddRubbishSection(
                    modifier = Modifier.height(SmartTheme.dimension.bucket.addRubbishPopupHeight),
                    count = rubbishPopupState.count,
                    rubbishName = rubbishPopupState.rubbishName,
                    selectClicked = { modeChange(RubbishPopupMode.SELECT) },
                    addClicked = addClicked,
                    cancelClicked = cancelClicked,
                    scanClicked = scanClicked,
                    increaseCount = increaseCount,
                    decreaseCount = decreaseCount
                )
            }

            RubbishPopupMode.SELECT -> {
                SelectRubbishSection(
                    modifier = Modifier.height(SmartTheme.dimension.bucket.addRubbishPopupHeight),
                    rubbishes = rubbishPopupState.rubbishesList,
                    selectedRubbishId = rubbishPopupState.rubbishId,
                    onRubbishClicked = { id ->
                        selectRubbish(id)
                        modeChange(RubbishPopupMode.ADD)
                    },
                )
            }
        }
    }
}

@Composable
private fun AddRubbishAnimatedContent(
    modifier: Modifier = Modifier,
    mode: RubbishPopupMode,
    content: @Composable AnimatedContentScope.(targetState: RubbishPopupMode) -> Unit,
) {
    AnimatedContent(
        modifier = modifier,
        targetState = mode,
        contentAlignment = Alignment.Center,
        transitionSpec = {
            if (targetState == RubbishPopupMode.ADD) {
                slideInHorizontally(
                    initialOffsetX = { -it * 2 },
                ) togetherWith slideOutHorizontally(
                    targetOffsetX = { it * 2 },
                )
            } else {
                slideInHorizontally(
                    initialOffsetX = { it * 2 },
                ) togetherWith slideOutHorizontally(
                    targetOffsetX = { -it * 2 },
                )
            }
        },
        label = "",
        content = content,
    )
}
