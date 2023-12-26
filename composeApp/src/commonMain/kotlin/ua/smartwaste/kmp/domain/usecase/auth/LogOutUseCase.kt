package ua.smartwaste.kmp.domain.usecase.auth

import ua.smartwaste.kmp.domain.usecase.core.ResultNoneParamsUseCase
import ua.smartwaste.kmp.domain.usecase.core.UseCaseLogger
import ua.smartwaste.kmp.preferences.api.AuthPreferences

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
