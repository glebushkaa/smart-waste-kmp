package ua.gleb.smartwaste.domain.result

import ua.gleb.smartwaste.domain.exception.LoginException

sealed class LoginResult {

    data class Success(val id: String) : LoginResult()

    data class Failure(val throwable: LoginException) : LoginResult()
}