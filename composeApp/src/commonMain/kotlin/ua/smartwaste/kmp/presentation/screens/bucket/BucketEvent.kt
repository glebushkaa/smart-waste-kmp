package ua.smartwaste.kmp.presentation.screens.bucket

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

sealed interface BucketEvent {

    data object ShowAddRubbishPopup : BucketEvent
    data object HideAddRubbishPopup : BucketEvent
}
