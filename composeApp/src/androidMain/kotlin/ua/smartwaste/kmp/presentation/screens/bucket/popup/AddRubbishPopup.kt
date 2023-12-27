@file:OptIn(ExperimentalMaterial3Api::class)

package ua.smartwaste.kmp.presentation.screens.bucket.popup

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ua.smartwaste.kmp.model.Rubbish
import ua.smartwaste.kmp.presentation.components.SmartButton
import ua.smartwaste.kmp.presentation.components.SmartIconButton
import ua.smartwaste.kmp.presentation.components.SmartOutlinedButton
import ua.smartwaste.kmp.presentation.core.ResourceType
import ua.smartwaste.kmp.presentation.core.extensions.clickableWithoutRipple
import ua.smartwaste.kmp.presentation.core.painterDrawableResource
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

enum class AddRubbishSheetMode {
    ADD,
    SELECT,
}

@Composable
actual fun AddRubbishPopup(
    modifier: Modifier,
    availableRubbishes: ImmutableList<Rubbish>,
    dismissRequest: () -> Unit,
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    var count by rememberSaveable {
        mutableIntStateOf(0)
    }
    var selectedId by rememberSaveable {
        mutableLongStateOf(availableRubbishes.firstOrNull()?.id ?: 0L)
    }
    var selectedName by rememberSaveable {
        mutableStateOf(availableRubbishes.firstOrNull()?.name ?: "")
    }
    var mode by rememberSaveable {
        mutableStateOf(AddRubbishSheetMode.ADD)
    }

    ModalBottomSheet(
        modifier = modifier.defaultMinSize(
            minHeight = 400.dp,
        ),
        onDismissRequest = dismissRequest,
        containerColor = SmartTheme.palette.surface,
        sheetState = modalBottomSheetState,
    ) {
        AnimatedContent(
            modifier = Modifier.padding(
                horizontal = SmartTheme.offset.width.huge,
            ),
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
        ) {
            when (it) {
                AddRubbishSheetMode.ADD -> AddRubbishSection(
                    modifier = Modifier.heightIn(min = 400.dp),
                    rubbishName = selectedName,
                    selectClicked = {
                        mode = AddRubbishSheetMode.SELECT
                    },
                    addClicked = {
                    },
                    cancelClicked = dismissRequest,
                    scanClicked = {
                    },
                )

                AddRubbishSheetMode.SELECT -> SelectRubbishSection(
                    modifier = Modifier.heightIn(max = 400.dp),
                    rubbishes = availableRubbishes,
                    selectedRubbishId = selectedId,
                    onRubbishClicked = {
                        mode = AddRubbishSheetMode.ADD
                        selectedId = it.id
                        selectedName = it.name
                    },
                )
            }
        }
    }
}

@Composable
private fun AddRubbishSection(
    modifier: Modifier = Modifier,
    rubbishName: String,
    selectClicked: () -> Unit,
    addClicked: () -> Unit,
    cancelClicked: () -> Unit,
    scanClicked: () -> Unit,
) {
    Column(modifier = modifier) {
        Text(
            text = "Add rubbish",
            style = SmartTheme.typography.headlineSmall,
            color = SmartTheme.palette.onSurface,
        )
        Spacer(modifier = Modifier.height(SmartTheme.offset.height.regular))
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            SmartButton(
                modifier = Modifier
                    .height(60.dp)
                    .weight(1f),
                content = {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = rubbishName,
                        style = SmartTheme.typography.bodyLarge,
                        color = SmartTheme.palette.onSurface,
                    )
                    Spacer(
                        modifier = Modifier.width(SmartTheme.offset.width.regular),
                    )
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterDrawableResource(
                            id = "ic_arrow_next",
                            type = ResourceType.XML,
                        ),
                        tint = SmartTheme.palette.onBackground,
                        contentDescription = null,
                    )
                },
                buttonColors = ButtonDefaults.buttonColors(
                    containerColor = SmartTheme.palette.background,
                ),
                onClick = selectClicked,
            )
            Spacer(
                modifier = Modifier.width(SmartTheme.offset.width.regular),
            )
            SmartIconButton(
                painterResource = painterDrawableResource(
                    id = "ic_scan",
                    type = ResourceType.XML,
                ),
                tint = SmartTheme.palette.onSurface,
                onClick = scanClicked,
            )
        }
        Spacer(
            modifier = Modifier.height(SmartTheme.offset.height.huge),
        )
        SmartOutlinedButton(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(),
            border = BorderStroke(
                width = 1.dp,
                color = SmartTheme.palette.primary,
            ),
            shape = SmartTheme.shape.medium,
            text = "Add",
            textStyle = SmartTheme.typography.bodyLarge,
            onClick = addClicked,
        )
        Spacer(
            modifier = Modifier.height(SmartTheme.offset.height.regular),
        )
        SmartButton(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(),
            text = "Cancel",
            textStyle = SmartTheme.typography.bodyLarge,
            buttonColors = ButtonDefaults.buttonColors(
                containerColor = SmartTheme.palette.error,
                contentColor = SmartTheme.palette.onError,
            ),
            onClick = cancelClicked,
        )
        Spacer(
            modifier = Modifier.height(SmartTheme.offset.height.large),
        )
    }
}

@Composable
private fun SelectRubbishSection(
    modifier: Modifier = Modifier,
    onRubbishClicked: (Rubbish) -> Unit = {},
    rubbishes: ImmutableList<Rubbish> = persistentListOf(),
    selectedRubbishId: Long? = null,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = "Rubbish",
            style = SmartTheme.typography.bodyLarge,
            color = SmartTheme.palette.onSurface,
        )
        Spacer(modifier = Modifier.height(SmartTheme.offset.height.regular))
        AvailableRubbishList(
            modifier = Modifier,
            onRubbishClicked = onRubbishClicked,
            rubbishes = rubbishes,
            selectedRubbishId = selectedRubbishId,
        )
        Spacer(modifier = Modifier.height(SmartTheme.offset.height.regular))
    }
}

@Composable
private fun AvailableRubbishList(
    modifier: Modifier = Modifier,
    selectedRubbishId: Long? = null,
    rubbishes: ImmutableList<Rubbish> = persistentListOf(),
    onRubbishClicked: (Rubbish) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(SmartTheme.offset.height.regular),
    ) {
        items(
            items = rubbishes,
            key = { it.id },
        ) {
            val color = if (it.id == selectedRubbishId) {
                SmartTheme.palette.primary.copy(0.5f)
            } else {
                SmartTheme.palette.background
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .background(
                        color = color,
                        shape = SmartTheme.shape.medium,
                    )
                    .clickableWithoutRipple {
                        onRubbishClicked(it)
                    },
                contentAlignment = Alignment.CenterStart,
            ) {
                Text(
                    modifier = Modifier.padding(
                        horizontal = SmartTheme.offset.width.regular,
                    ),
                    text = it.name,
                    style = SmartTheme.typography.bodyLarge,
                    color = SmartTheme.palette.onBackground,
                )
            }
        }
    }
}
