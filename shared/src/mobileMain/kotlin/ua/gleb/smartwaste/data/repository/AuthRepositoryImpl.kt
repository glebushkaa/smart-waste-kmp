package ua.gleb.smartwaste.data.repository

import io.ktor.client.plugins.ResponseException
import ua.gleb.smartwaste.domain.exception.AuthException
import ua.gleb.smartwaste.domain.exception.LoginException
import ua.gleb.smartwaste.domain.exception.LoginField
import ua.gleb.smartwaste.domain.repository.AuthRepository
import ua.gleb.smartwaste.network.api.auth.AuthApi
import ua.gleb.smartwaste.network.api.auth.model.AuthResponse

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

class AuthRepositoryImpl(
    private val authApi: AuthApi,
) : AuthRepository {

    override suspend fun login(email: String, password: String): String {
        val response: AuthResponse
        try {
            response = authApi.login(email, password)
        } catch (exception: ResponseException) {
            val errorResponse = exception.response.status
            throw getLoginException(errorResponse.value.toString(), exception.message)
        }
        return response.accessToken ?: run {
            val exception = AuthException(
                code = LOGIN_EXCEPTION,
                message = response.message ?: "Unknown error",
            )
            throw exception
        }
    }

    override suspend fun register(username: String, email: String, password: String): String {
        val response: AuthResponse
        try {
            response = authApi.register(username, email, password)
        } catch (exception: ResponseException) {
            val errorResponse = exception.response.status
            throw getLoginException(errorResponse.value.toString(), exception.message)
        }
        return response.accessToken ?: run {
            val exception = AuthException(
                code = REGISTER_EXCEPTION,
                message = response.message ?: "Unknown error",
            )
            throw exception
        }
    }

    private fun getLoginException(code: String, message: String?) = when (code) {
        PASSWORD_IS_NOT_VALID -> LoginException(
            field = LoginField.PASSWORD,
            message = message ?: "Invalid password",
        )

        USER_NOT_FOUND -> LoginException(
            field = LoginField.EMAIL,
            message = message ?: "User not found",
        )

        EMAIL_NOT_UNIQUE -> LoginException(
            field = LoginField.EMAIL,
            message = message ?: "Email is already taken",
        )

        USERNAME_NOT_UNIQUE -> LoginException(
            field = LoginField.USERNAME,
            message = message ?: "Username is already taken",
        )

        else -> LoginException(
            message = message ?: "Unknown error"
        )
    }

    private companion object {
        const val LOGIN_EXCEPTION = 100
        const val REGISTER_EXCEPTION = 200

        const val PASSWORD_IS_NOT_VALID = "invalid-password"
        const val USER_NOT_FOUND = "user-not-found"
        const val EMAIL_NOT_UNIQUE = "email-not-unique"
        const val USERNAME_NOT_UNIQUE = "username-not-unique"
    }
}
