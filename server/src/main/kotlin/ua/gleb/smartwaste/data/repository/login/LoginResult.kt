package ua.gleb.smartwaste.data.repository.login

import ua.gleb.smartwaste.domain.exception.LoginException

sealed class LoginResult {

    data class Success(val id: String) : LoginResult()

    data class Failure(val throwable: LoginException) : LoginResult()
}