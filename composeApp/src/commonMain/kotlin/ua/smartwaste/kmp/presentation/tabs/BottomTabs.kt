package ua.smartwaste.kmp.presentation.tabs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import ua.smartwaste.kmp.presentation.core.EIGHT_HUNDRED_MILLIS
import ua.smartwaste.kmp.presentation.core.FIVE_HUNDRED_MILLIS
import ua.smartwaste.kmp.presentation.core.ResourceType
import ua.smartwaste.kmp.presentation.core.extensions.clickableWithoutRipple
import ua.smartwaste.kmp.presentation.core.painterDrawableResource
import ua.smartwaste.kmp.presentation.screens.bucket.BucketScreen
import ua.smartwaste.kmp.presentation.screens.profile.ProfileScreen
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */
@Composable
fun AnimatedBottomTabs(
    modifier: Modifier = Modifier,
    navigator: Navigator,
    visible: Boolean = false,
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = expandVertically(
            expandFrom = Alignment.Bottom,
            animationSpec = tween(FIVE_HUNDRED_MILLIS.toInt()),
        ) + fadeIn(
            animationSpec = tween(EIGHT_HUNDRED_MILLIS.toInt()),
        ),
        exit = shrinkVertically(
            shrinkTowards = Alignment.Bottom,
            animationSpec = tween(FIVE_HUNDRED_MILLIS.toInt()),
        ) + fadeOut(
            animationSpec = tween(EIGHT_HUNDRED_MILLIS.toInt()),
        ),
    ) {
        SmartBottomTabs(
            modifier = Modifier,
            navigator = navigator,
        )
    }
}

@Composable
private fun SmartBottomTabs(
    modifier: Modifier = Modifier,
    navigator: Navigator,
) {
    Row(
        modifier = modifier
            .height(70.dp)
            .fillMaxWidth()
            .background(SmartTheme.palette.surface),
    ) {
        BottomTab(
            iconPainter = painterDrawableResource(
                id = "ic_profile",
                type = ResourceType.XML,
            ),
            label = "Profile",
            selected = navigator.lastItem is ProfileScreen,
            onClick = {
                navigator.replaceAll(ProfileScreen)
            },
        )
        BottomTab(
            iconPainter = painterDrawableResource(
                id = "ic_bucket",
                type = ResourceType.XML,
            ),
            label = "Bucket",
            selected = navigator.lastItem is BucketScreen,
            onClick = {
                navigator.replaceAll(BucketScreen)
            },
        )
    }
}

@Composable
private fun RowScope.BottomTab(
    modifier: Modifier = Modifier,
    iconPainter: Painter,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val primaryColor = SmartTheme.palette.primary
    val onSurfaceColor = SmartTheme.palette.onSurface
    val color by remember(selected) {
        derivedStateOf {
            if (selected) primaryColor else onSurfaceColor.copy(alpha = 0.5f)
        }
    }

    Column(
        modifier = modifier
            .weight(1f)
            .fillMaxHeight()
            .clickableWithoutRipple(onClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = iconPainter,
            contentDescription = null,
            tint = color,
        )
        Text(
            text = label,
            color = color,
            style = SmartTheme.typography.bodyMedium,
        )
    }
}
