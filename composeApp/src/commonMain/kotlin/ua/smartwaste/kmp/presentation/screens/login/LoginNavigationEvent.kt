package ua.smartwaste.kmp.presentation.screens.login

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

sealed interface LoginNavigationEvent {

    data object NavigateToProfile : LoginNavigationEvent
}
