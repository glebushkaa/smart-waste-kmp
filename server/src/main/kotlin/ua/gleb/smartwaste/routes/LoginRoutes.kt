package ua.gleb.smartwaste.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ua.gleb.smartwaste.data.repository.login.LoginRepository
import ua.gleb.smartwaste.data.repository.login.LoginResult
import ua.gleb.smartwaste.network.AuthRoutes
import ua.gleb.smartwaste.network.Routes
import ua.gleb.smartwaste.network.auth.dto.LoginDto
import ua.gleb.smartwaste.network.auth.dto.RegisterDto
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
        val params = call.receive<RegisterDto>()
        val result = loginRepository.register(params.username, params.email, params.password)
        when (result) {
            is LoginResult.Failure -> {
                call.handleFailureLoginResult(result)
            }

            is LoginResult.Success -> {
                val token = generateToken(result.id)
                call.respond(HttpStatusCode.Created, hashMapOf("token" to token))
            }
        }
    }
}

private fun Route.signInRoute(
    loginRepository: LoginRepository
) {
    post(AuthRoutes.SIGN_IN.route) {
        val params = call.receive<LoginDto>()
        val result = loginRepository.login(params.email, params.password)
        when (result) {
            is LoginResult.Failure -> {
                call.handleFailureLoginResult(result)
            }

            is LoginResult.Success -> {
                val token = generateToken(result.id)
                call.respond(HttpStatusCode.Created, hashMapOf("token" to token))
            }
        }
    }
}

private suspend fun ApplicationCall.handleFailureLoginResult(result: LoginResult.Failure) {
    respond(
        result.throwable.statusCode,
        hashMapOf("message" to result.throwable.message),
    )
    throw result.throwable
}