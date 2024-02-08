package ua.gleb.smartwaste.core.extensions

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import ua.gleb.smartwaste.domain.exception.MissingParamException

suspend fun ApplicationCall.getParamOrThrow(params: Parameters, query: String): String {
    return params[query] ?: run {
        respond(
            status = HttpStatusCode.BadRequest,
            message = hashMapOf(
                "message" to "Missing $query"
            )
        )
        throw MissingParamException("Missing $query")
    }
}

fun ApplicationCall.getIdFromToken(): String {
    val principal = principal<JWTPrincipal>()
    return principal?.payload?.getClaim("id")?.asString() ?: run {
        throw Throwable("id from token is null")
    }
}