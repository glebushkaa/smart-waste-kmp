package ua.smartwaste.kmp.presentation.screens.bucket.popup

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ua.smartwaste.kmp.model.Rubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

@Composable
expect fun AddRubbishPopup(
    modifier: Modifier = Modifier,
    availableRubbishes: ImmutableList<Rubbish> = persistentListOf(),
    dismissRequest: () -> Unit,
)
