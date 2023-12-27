package ua.smartwaste.kmp.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import ua.smartwaste.kmp.presentation.core.FIVE_HUNDRED_MILLIS
import ua.smartwaste.kmp.presentation.core.ResourceType
import ua.smartwaste.kmp.presentation.core.painterDrawableResource
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

@Composable
fun AnimatedTopBar(
    modifier: Modifier = Modifier,
    visible: Boolean,
    logOutVisible: Boolean = false,
    logOut: () -> Unit = {},
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(FIVE_HUNDRED_MILLIS.toInt()),
        ),
        exit = shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = tween(FIVE_HUNDRED_MILLIS.toInt()),
        ),
    ) {
        SmartTopBar(
            logOutVisible = logOutVisible,
            logOut = logOut,
        )
    }
}

@Composable
private fun SmartTopBar(
    modifier: Modifier = Modifier,
    logOutVisible: Boolean = false,
    logOut: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .height(SmartTheme.dimension.components.topBarHeight)
            .fillMaxWidth()
            .background(SmartTheme.palette.surface),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .padding(start = SmartTheme.offset.width.regular)
                .size(SmartTheme.dimension.components.topBarImageSize),
            painter = painterDrawableResource(id = "img_recycle"),
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .padding(horizontal = SmartTheme.offset.width.medium)
                .weight(1f),
            text = "SmartWaste",
            style = SmartTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold,
            ),
            color = SmartTheme.palette.onBackground,
        )
        AnimatedVisibility(logOutVisible) {
            SmartIconButton(
                modifier = Modifier
                    .padding(end = SmartTheme.offset.width.regular)
                    .size(SmartTheme.dimension.components.topBarImageSize),
                painterResource = painterDrawableResource(
                    id = "ic_log_out",
                    type = ResourceType.XML,
                ),
                onClick = logOut,
                tint = SmartTheme.palette.error,
            )
        }
    }
}
