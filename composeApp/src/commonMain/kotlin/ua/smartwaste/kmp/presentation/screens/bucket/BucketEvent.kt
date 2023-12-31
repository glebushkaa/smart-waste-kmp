package ua.smartwaste.kmp.presentation.screens.bucket

import ua.smartwaste.kmp.model.Rubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

sealed interface BucketEvent {

    data object ShowAddRubbishPopup : BucketEvent
    data object HideAddRubbishPopup : BucketEvent
    data class AddRubbish(
        val id: Long,
        val count: Int
    ) : BucketEvent

    data class DecreaseCount(val id: Long) : BucketEvent
    data class IncreaseCount(val id: Long) : BucketEvent
}
