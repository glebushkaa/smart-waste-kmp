package ua.smartwaste.kmp.presentation.screens.profile

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

sealed interface ProfileEvent {

    data object LogOut : ProfileEvent
}
