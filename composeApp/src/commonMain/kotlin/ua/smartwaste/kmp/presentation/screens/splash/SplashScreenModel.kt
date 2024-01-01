package ua.smartwaste.kmp.presentation.screens.splash

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ua.smartwaste.kmp.domain.usecase.user.GetUserUseCase
import ua.smartwaste.kmp.presentation.core.modelScope

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

class SplashScreenModel(
    private val getUserUseCase: GetUserUseCase,
) : ScreenModel {

    private val _navigationEvent = Channel<SplashNavigationEvent>()
    val navigationEvent = _navigationEvent.receiveAsFlow()

    fun sendEvent(event: SplashEvent) {
        when (event) {
            is SplashEvent.ValidateToken -> validateToken()
        }
    }

    private fun validateToken() = modelScope.launch {
        val result = getUserUseCase()
        val effect = if (result.isSuccess) {
            SplashNavigationEvent.NavigateToProfile
        } else {
            SplashNavigationEvent.NavigateToLogin
        }
        _navigationEvent.trySend(effect)
    }
}
