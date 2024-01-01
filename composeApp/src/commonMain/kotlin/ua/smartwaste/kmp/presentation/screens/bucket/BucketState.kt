package ua.smartwaste.kmp.presentation.screens.bucket

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ua.smartwaste.kmp.model.Rubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

@Stable
data class BucketState(
    val selectedRubbishes: ImmutableList<Rubbish> = persistentListOf(),
    val loaderVisible: Boolean = false,
    val rubbishPopupState: RubbishPopupState = RubbishPopupState(),
) {
    @Stable
    data class RubbishPopupState(
        val visible: Boolean = false,
        val mode: RubbishPopupMode = RubbishPopupMode.ADD,
        val count: Int = 0,
        val rubbishName: String = "",
        val rubbishId: Long = 0,
        val rubbishesList: ImmutableList<Rubbish> = persistentListOf(),
    )
}
