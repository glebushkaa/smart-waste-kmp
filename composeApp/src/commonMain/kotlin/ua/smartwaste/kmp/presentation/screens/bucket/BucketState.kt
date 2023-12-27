package ua.smartwaste.kmp.presentation.screens.bucket

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ua.smartwaste.kmp.model.Rubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

data class BucketState(
    val selectedRubbishList: ImmutableList<Rubbish> = persistentListOf(),
    val availableRubbishList: ImmutableList<Rubbish> = persistentListOf(),
    val loaderVisible: Boolean = false,
    val rubbishPopupVisible: Boolean = false,
)
