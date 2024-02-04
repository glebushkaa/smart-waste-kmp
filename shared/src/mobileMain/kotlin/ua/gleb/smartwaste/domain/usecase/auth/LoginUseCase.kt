package ua.gleb.smartwaste.domain.usecase.auth

import ua.gleb.smartwaste.domain.repository.AuthRepository
import ua.gleb.smartwaste.domain.usecase.core.ResultUseCase
import ua.gleb.smartwaste.domain.usecase.core.UseCase
import ua.gleb.smartwaste.domain.usecase.core.UseCaseLogger
import ua.gleb.smartwaste.preferences.api.AuthPreferences

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

class LoginUseCase(
    private val authRepository: ua.gleb.smartwaste.domain.repository.AuthRepository,
    private val authPreferences: AuthPreferences,
    useCaseLogger: UseCaseLogger,
) : ResultUseCase<Unit, LoginUseCase.Params>(useCaseLogger) {

    override suspend fun invoke(params: Params) = runCatching {
        val token = authRepository.login(params.email, params.password)
        authPreferences.setToken(token)
    }

    data class Params(
        val email: String,
        val password: String,
    ) : UseCase.Params
}
