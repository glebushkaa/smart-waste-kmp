package ua.smartwaste.kmp.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import ua.smartwaste.kmp.domain.usecase.auth.LogOutUseCase

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

class MainScreenModel(
    private val logOutUseCase: LogOutUseCase,
) : ScreenModel {

    fun logOut() = screenModelScope.launch {
        logOutUseCase()
    }
}
