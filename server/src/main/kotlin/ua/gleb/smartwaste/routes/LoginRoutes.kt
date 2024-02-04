package ua.gleb.smartwaste.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ua.gleb.smartwaste.core.extensions.getParamOrThrow
import ua.gleb.smartwaste.data.repository.login.LoginRepository
import ua.gleb.smartwaste.data.repository.login.LoginResult
import ua.gleb.smartwaste.plugins.auth.TokenManager.generateToken

fun Routing.loginRoute() {
    val loginRepository by inject<LoginRepository>()
    route("/login/") {
        signInRoute(loginRepository)
        signUpRoute(loginRepository)
    }
}

private fun Route.signUpRoute(
    loginRepository: LoginRepository
) {
    post("signup") {
        val params = call.receiveParameters()
        val email = call.getParamOrThrow(params, "email")
        val name = call.getParamOrThrow(params, "name")
        val password = call.getParamOrThrow(params, "password")
        val result = loginRepository.register(name, email, password)
        if (result is LoginResult.Failure) call.handleFailureLoginResult(result)
        val token = generateToken(email)
        call.respond(hashMapOf("token" to token))
    }
}

private fun Route.signInRoute(
    loginRepository: LoginRepository
) {
    post("signin") {
        val params = call.receiveParameters()
        val email = call.getParamOrThrow(params, "email")
        val password = call.getParamOrThrow(params, "password")
        val result = loginRepository.login(email, password)
        if (result is LoginResult.Failure) call.handleFailureLoginResult(result)
        val token = generateToken(email)
        call.respond(hashMapOf("token" to token))
    }
}

private suspend fun ApplicationCall.handleFailureLoginResult(result: LoginResult.Failure) {
    respondText(
        text = result.throwable.message ?: "User not found",
        status = HttpStatusCode.Unauthorized
    )
    throw result.throwable
}