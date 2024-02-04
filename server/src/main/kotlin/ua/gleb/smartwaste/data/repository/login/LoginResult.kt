package ua.gleb.smartwaste.data.repository.login

sealed class LoginResult {

    data object Success: LoginResult()

    data class Failure(val throwable: Throwable): LoginResult()
}