package ua.smartwaste.kmp.presentation.screens.splash

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

sealed interface SplashEvent {

    data object ValidateToken : SplashEvent
}
