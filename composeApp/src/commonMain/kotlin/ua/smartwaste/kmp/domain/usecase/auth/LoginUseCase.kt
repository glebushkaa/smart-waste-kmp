package ua.smartwaste.kmp.domain.usecase.auth

import ua.smartwaste.kmp.domain.repository.AuthRepository
import ua.smartwaste.kmp.domain.usecase.auth.LoginUseCase.Params
import ua.smartwaste.kmp.domain.usecase.core.ResultUseCase
import ua.smartwaste.kmp.domain.usecase.core.UseCase
import ua.smartwaste.kmp.domain.usecase.core.UseCaseLogger
import ua.smartwaste.kmp.preferences.api.AuthPreferences

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

class LoginUseCase(
    private val authRepository: AuthRepository,
    private val authPreferences: AuthPreferences,
    useCaseLogger: UseCaseLogger,
) : ResultUseCase<Unit, Params>(useCaseLogger) {

    override suspend fun invoke(params: Params) = runCatching {
        val token = authRepository.login(params.email, params.password)
        authPreferences.setToken(token)
    }

    data class Params(
        val email: String,
        val password: String,
    ) : UseCase.Params
}
