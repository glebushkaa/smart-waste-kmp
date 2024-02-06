package ua.gleb.smartwaste.domain.exception

import io.ktor.http.*

data class LoginException(
    override val message: String,
    val statusCode: HttpStatusCode
) : Throwable() {
    override fun toString() = "LoginException(statusCode='${statusCode.value}', message='$message')"
}