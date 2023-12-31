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
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ua.smartwaste.kmp.model.Rubbish
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

enum class AddRubbishSheetMode { ADD, SELECT, }

@Composable
expect fun AddRubbishPopup(
    modifier: Modifier = Modifier,
    availableRubbishes: ImmutableList<Rubbish> = persistentListOf(),
    dismissRequest: () -> Unit,
    addClicked: (Long, Int) -> Unit,
)

@Composable
fun AddRubbishPopupContent(
    modifier: Modifier = Modifier,
    availableRubbishes: ImmutableList<Rubbish> = persistentListOf(),
    cancelClicked: () -> Unit,
    addClicked: (Long, Int) -> Unit,
    scanClicked: () -> Unit,
) {
    var selectedRubbish by remember {
        mutableStateOf(availableRubbishes.firstOrNull())
    }
    var mode by rememberSaveable {
        mutableStateOf(AddRubbishSheetMode.ADD)
    }
    AddRubbishAnimatedContent(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = SmartTheme.palette.surface,
                shape = SmartTheme.shape.large,
            )
            .padding(
                horizontal = SmartTheme.offset.width.huge,
            ),
        mode = mode,
    ) {
        when (it) {
            AddRubbishSheetMode.ADD -> AddRubbishSection(
                modifier = Modifier.height(SmartTheme.dimension.bucket.addRubbishPopupHeight),
                rubbishName = selectedRubbish?.name ?: "",
                selectClicked = { mode = AddRubbishSheetMode.SELECT },
                addClicked = { count ->
                    val id = selectedRubbish?.id ?: 0
                    addClicked(id, count)
                },
                cancelClicked = cancelClicked,
                scanClicked = scanClicked,
            )

            AddRubbishSheetMode.SELECT -> SelectRubbishSection(
                modifier = Modifier.height(SmartTheme.dimension.bucket.addRubbishPopupHeight),
                rubbishes = availableRubbishes,
                selectedRubbishId = selectedRubbish?.id ?: 0,
                onRubbishClicked = { rubbish ->
                    mode = AddRubbishSheetMode.ADD
                    selectedRubbish = rubbish
                },
            )
        }
    }
}

@Composable
private fun AddRubbishAnimatedContent(
    modifier: Modifier = Modifier,
    mode: AddRubbishSheetMode,
    content: @Composable AnimatedContentScope.(targetState: AddRubbishSheetMode) -> Unit,
) {
    AnimatedContent(
        modifier = modifier,
        targetState = mode,
        contentAlignment = Alignment.Center,
        transitionSpec = {
            if (targetState == AddRubbishSheetMode.ADD) {
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
