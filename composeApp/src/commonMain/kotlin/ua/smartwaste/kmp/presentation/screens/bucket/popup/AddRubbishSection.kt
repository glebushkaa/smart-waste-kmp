package ua.smartwaste.kmp.presentation.screens.bucket.popup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ua.smartwaste.kmp.presentation.components.SmartButton
import ua.smartwaste.kmp.presentation.components.SmartIconButton
import ua.smartwaste.kmp.presentation.components.SmartOutlinedButton
import ua.smartwaste.kmp.presentation.core.ResourceType
import ua.smartwaste.kmp.presentation.core.painterDrawableResource
import ua.smartwaste.kmp.presentation.screens.bucket.BucketEvent
import ua.smartwaste.kmp.presentation.screens.bucket.components.DEFAULT_MAX_VALUE
import ua.smartwaste.kmp.presentation.screens.bucket.components.DEFAULT_MIN_VALUE
import ua.smartwaste.kmp.presentation.screens.bucket.components.RubbishCounter
import ua.smartwaste.kmp.presentation.theme.SmartTheme
import kotlin.math.max
import kotlin.math.min

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/28/2023
 */

@Composable
fun AddRubbishSection(
    modifier: Modifier = Modifier,
    count: Int,
    increaseCount: () -> Unit,
    decreaseCount: () -> Unit,
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
            SelectRubbishButton(
                modifier = Modifier.weight(1f),
                rubbishName = rubbishName,
                selectClicked = selectClicked,
            )
            Spacer(modifier = Modifier.width(SmartTheme.offset.width.regular))
            SmartIconButton(
                painterResource = painterDrawableResource(
                    id = "ic_scan",
                    type = ResourceType.XML,
                ),
                tint = SmartTheme.palette.onSurface,
                onClick = scanClicked,
            )
        }
        Spacer(modifier = Modifier.height(SmartTheme.offset.height.regular))
        RubbishCounter(
            modifier = Modifier
                .height(SmartTheme.dimension.bucket.counterHeight)
                .fillMaxWidth()
                .background(
                    color = SmartTheme.palette.surface,
                    shape = SmartTheme.shape.medium,
                ),
            count = count,
            increaseClicked = increaseCount,
            decreaseClicked = decreaseCount,
        )
        Spacer(modifier = Modifier.height(SmartTheme.offset.height.regular))
        SmartOutlinedButton(
            modifier = Modifier
                .height(SmartTheme.dimension.bucket.addButtonHeight)
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
        Spacer(modifier = Modifier.height(SmartTheme.offset.height.regular))
        SmartButton(
            modifier = Modifier
                .height(SmartTheme.dimension.bucket.cancelButtonHeight)
                .fillMaxWidth(),
            text = "Cancel",
            textStyle = SmartTheme.typography.bodyLarge,
            buttonColors = ButtonDefaults.buttonColors(
                containerColor = SmartTheme.palette.error,
                contentColor = SmartTheme.palette.onError,
            ),
            onClick = cancelClicked,
        )
        Spacer(modifier = Modifier.height(SmartTheme.offset.height.large))
    }
}

@Composable
private fun SelectRubbishButton(
    modifier: Modifier = Modifier,
    rubbishName: String,
    selectClicked: () -> Unit,
) {
    SmartButton(
        modifier = modifier.height(
            SmartTheme.dimension.bucket.selectRubbishButtonHeight
        ),
        content = {
            Text(
                modifier = Modifier.weight(1f),
                text = rubbishName,
                maxLines = 1,
                style = SmartTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
                color = SmartTheme.palette.onSurface,
            )
            Spacer(modifier = Modifier.width(SmartTheme.offset.width.regular))
            Icon(
                modifier = Modifier.size(
                    SmartTheme.dimension.bucket.arrowNextIconSize
                ),
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
}
