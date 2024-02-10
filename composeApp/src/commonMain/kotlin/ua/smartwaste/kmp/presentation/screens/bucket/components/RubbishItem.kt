package ua.smartwaste.kmp.presentation.screens.bucket.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ua.gleb.smartwaste.model.Rubbish
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
    Row(
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth()
            .background(
                color = SmartTheme.palette.surface,
                shape = SmartTheme.shape.medium,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = SmartTheme.offset.width.large)
                .weight(1f),
            text = item.title,
            style = SmartTheme.typography.bodyLargeBold,
            color = SmartTheme.palette.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        RubbishCounter(
            modifier = Modifier.padding(end = SmartTheme.offset.width.regular),
            decreaseClicked = decreaseCountClicked,
            increaseClicked = increaseCountClicked,
            count = item.count,
        )
    }
}
