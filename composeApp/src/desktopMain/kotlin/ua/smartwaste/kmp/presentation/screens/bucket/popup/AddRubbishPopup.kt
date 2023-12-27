package ua.smartwaste.kmp.presentation.screens.bucket.popup

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import ua.smartwaste.kmp.model.Rubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

@Composable
actual fun AddRubbishPopup(
    modifier: Modifier,
    availableRubbishes: ImmutableList<Rubbish>,
    dismissRequest: () -> Unit,
) {
}
