package ua.gleb.smartwaste.domain.usecase.auth

import ua.gleb.smartwaste.domain.usecase.core.ResultNoneParamsUseCase
import ua.gleb.smartwaste.domain.usecase.core.UseCaseLogger
import ua.gleb.smartwaste.preferences.api.AuthPreferences

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

class LogOutUseCase(
    private val authPreferences: AuthPreferences,
    useCaseLogger: UseCaseLogger,
) : ResultNoneParamsUseCase<Unit>(useCaseLogger) {

    override suspend fun invoke() = runCatching {
        authPreferences.removeToken()
    }
}
