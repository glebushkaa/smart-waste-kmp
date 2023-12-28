package ua.smartwaste.kmp.presentation.screens.bucket.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import ua.smartwaste.kmp.presentation.components.SmartOutlinedIconButton
import ua.smartwaste.kmp.presentation.core.ResourceType
import ua.smartwaste.kmp.presentation.core.painterDrawableResource
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/28/2023
 */

@Composable
fun RubbishCounter(
    modifier: Modifier = Modifier,
    count: Int,
    increaseClicked: () -> Unit,
    decreaseClicked: () -> Unit,
    min: Int = DEFAULT_MIN_VALUE,
    max: Int = DEFAULT_MAX_VALUE,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
    ) {
        SmartOutlinedIconButton(
            modifier = Modifier
                .padding(end = SmartTheme.offset.width.regular)
                .size(SmartTheme.dimension.bucket.counterButtonSize)
                .align(Alignment.CenterVertically),
            painterResource = painterDrawableResource(id = "ic_minus", type = ResourceType.XML),
            onClick = decreaseClicked,
            enabled = count > min,
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .width(SmartTheme.dimension.bucket.counterTextWidth),
            text = count.toString(),
            style = SmartTheme.typography.headlineSmall,
            color = SmartTheme.palette.onSurface,
            textAlign = TextAlign.Center,
        )
        SmartOutlinedIconButton(
            modifier = Modifier
                .padding(horizontal = SmartTheme.offset.width.regular)
                .size(SmartTheme.dimension.bucket.counterButtonSize)
                .align(Alignment.CenterVertically),
            painterResource = painterDrawableResource(id = "ic_plus", type = ResourceType.XML),
            onClick = increaseClicked,
            enabled = count < max,
        )
    }
}

const val DEFAULT_MIN_VALUE = 0
const val DEFAULT_MAX_VALUE = 10
