package ua.smartwaste.kmp.presentation

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.launch
import ua.gleb.smartwaste.domain.usecase.auth.LogOutUseCase
import ua.smartwaste.kmp.presentation.core.modelScope

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

class MainScreenModel(
    private val logOutUseCase: ua.gleb.smartwaste.domain.usecase.auth.LogOutUseCase,
) : ScreenModel {

    fun logOut() = modelScope.launch {
        logOutUseCase()
    }
}
