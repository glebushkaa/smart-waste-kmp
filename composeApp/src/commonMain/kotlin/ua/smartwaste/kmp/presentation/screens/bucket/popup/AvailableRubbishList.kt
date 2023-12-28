package ua.smartwaste.kmp.presentation.screens.bucket.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ua.smartwaste.kmp.model.Rubbish
import ua.smartwaste.kmp.presentation.core.extensions.clickableWithoutRipple
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/28/2023
 */

@Composable
fun AvailableRubbishList(
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
        item {
            Spacer(
                modifier = Modifier.height(
                    SmartTheme.offset.height.regular,
                ),
            )
        }
    }
}
