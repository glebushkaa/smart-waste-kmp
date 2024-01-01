package ua.smartwaste.kmp.presentation.screens.bucket.popup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ua.smartwaste.kmp.model.Rubbish
import ua.smartwaste.kmp.presentation.theme.SmartTheme

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/28/2023
 */

@Composable
fun SelectRubbishSection(
    modifier: Modifier = Modifier,
    onRubbishClicked: (Long) -> Unit = {},
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
            modifier = Modifier.nestedScroll(
                object : NestedScrollConnection {
                    override fun onPostScroll(
                        consumed: Offset,
                        available: Offset,
                        source: NestedScrollSource,
                    ) = available
                },
            ),
            onRubbishClicked = onRubbishClicked,
            rubbishes = rubbishes,
            selectedRubbishId = selectedRubbishId,
        )
    }
}
