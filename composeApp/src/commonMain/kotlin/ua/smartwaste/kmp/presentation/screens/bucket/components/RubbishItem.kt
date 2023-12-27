package ua.smartwaste.kmp.presentation.screens.bucket.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ua.smartwaste.kmp.model.Rubbish
import ua.smartwaste.kmp.presentation.components.SmartOutlinedIconButton
import ua.smartwaste.kmp.presentation.core.ResourceType
import ua.smartwaste.kmp.presentation.core.painterDrawableResource
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

@Composable
fun RubbishItem(
    modifier: Modifier = Modifier,
    item: Rubbish,
    decreaseCountClicked: () -> Unit = {},
    increaseCountClicked: () -> Unit = {},
) {
    val decreaseCountEnabled by remember {
        derivedStateOf { item.count > 0 }
    }
    val increaseCountEnabled by remember {
        derivedStateOf { item.count < item.limit }
    }

    Row(
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth()
            .background(
                color = SmartTheme.palette.surface,
                shape = SmartTheme.shape.medium,
            ),
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = SmartTheme.offset.width.large)
                .align(Alignment.CenterVertically)
                .weight(1f),
            text = item.name,
            style = SmartTheme.typography.bodyLargeBold,
            color = SmartTheme.palette.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        SmartOutlinedIconButton(
            modifier = Modifier
                .padding(end = SmartTheme.offset.width.regular)
                .size(SmartTheme.dimension.bucket.rubbishItemCountButtonSize)
                .align(Alignment.CenterVertically),
            painterResource = painterDrawableResource("ic_minus", ResourceType.XML),
            onClick = decreaseCountClicked,
            enabled = decreaseCountEnabled,
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .width(SmartTheme.dimension.bucket.rubbishItemTextHeight),
            text = item.count.toString(),
            style = SmartTheme.typography.headlineSmall,
            color = SmartTheme.palette.onSurface,
            textAlign = TextAlign.Center,
        )
        SmartOutlinedIconButton(
            modifier = Modifier
                .padding(horizontal = SmartTheme.offset.width.regular)
                .size(SmartTheme.dimension.bucket.rubbishItemCountButtonSize)
                .align(Alignment.CenterVertically),
            painterResource = painterDrawableResource("ic_plus", ResourceType.XML),
            onClick = increaseCountClicked,
            enabled = increaseCountEnabled,
        )
    }
}
