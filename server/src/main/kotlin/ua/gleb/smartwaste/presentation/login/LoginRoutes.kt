package ua.gleb.smartwaste.presentation.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ua.gleb.smartwaste.network.auth.response.ErrorResponse
import ua.gleb.smartwaste.network.auth.response.TokenResponse
import ua.gleb.smartwaste.domain.repository.LoginRepository
import ua.gleb.smartwaste.domain.result.LoginResult
import ua.gleb.smartwaste.network.AuthRoutes
import ua.gleb.smartwaste.network.Routes
import ua.gleb.smartwaste.network.auth.request.LoginRequest
import ua.gleb.smartwaste.network.auth.request.RegisterRequest
import ua.gleb.smartwaste.plugins.auth.TokenManager.generateToken

fun Routing.loginRoute() {
    val loginRepository by inject<LoginRepository>()
    route("/${Routes.AUTH.route}/") {
        signInRoute(loginRepository)
        signUpRoute(loginRepository)
    }
}

private fun Route.signUpRoute(
    loginRepository: LoginRepository
) {
    post(AuthRoutes.SIGN_UP.route) {
        val params = call.receive<RegisterRequest>()
        val result = loginRepository.register(params.username, params.email, params.password)
        when (result) {
            is LoginResult.Failure -> {
                call.handleFailureLoginResult(result)
            }

            is LoginResult.Success -> {
                val token = call.generateToken(result)
                call.respond(HttpStatusCode.Created, TokenResponse(token))
            }
        }
    }
}

private fun Route.signInRoute(
    loginRepository: LoginRepository
) {
    post(AuthRoutes.SIGN_IN.route) {
        val params = call.receive<LoginRequest>()
        val result = loginRepository.login(params.email, params.password)
        when (result) {
            is LoginResult.Failure -> {
                call.handleFailureLoginResult(result)
            }

            is LoginResult.Success -> {
                val token = call.generateToken(result)
                call.respond(HttpStatusCode.Created, TokenResponse(token))
            }
        }
    }
}

private suspend fun ApplicationCall.generateToken(result: LoginResult.Success): String {
    return generateToken(result.id) ?: run {
        val throwable = Throwable("token is null")
        respond(
            status = HttpStatusCode.UnprocessableEntity,
            message = ErrorResponse(throwable.message ?: "")
        )
        throw throwable
    }
}

private suspend fun ApplicationCall.handleFailureLoginResult(result: LoginResult.Failure) {
    respond(
        result.throwable.statusCode,
        ErrorResponse(result.throwable.message)
    )
    throw result.throwable
}