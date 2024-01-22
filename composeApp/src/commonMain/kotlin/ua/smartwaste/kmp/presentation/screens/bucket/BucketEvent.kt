package ua.smartwaste.kmp.presentation.screens.bucket

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

sealed interface BucketEvent {

    data object ShowAddRubbishPopup : BucketEvent
    data object HideRubbishPopup : BucketEvent
    data object AddRubbish : BucketEvent
    data object ScanClicked : BucketEvent

    data class DecreaseCount(val id: Long) : BucketEvent
    data class IncreaseCount(val id: Long) : BucketEvent

    data class ChangeRubbishPopupMode(val mode: RubbishPopupMode) : BucketEvent
    data class SelectRubbish(val id: Long) : BucketEvent

    data object IncreaseRubbishPopupCount : BucketEvent
    data object DecreaseRubbishPopupCount : BucketEvent

    data class ProcessScannedItemPath(
        val path: String
    ) : BucketEvent
    data class SendToast(val message: String): BucketEvent

    data object ClearSideEffect: BucketEvent
}
