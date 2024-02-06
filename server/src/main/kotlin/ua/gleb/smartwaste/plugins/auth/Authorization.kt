package ua.gleb.smartwaste.plugins.auth

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import ua.gleb.smartwaste.plugins.auth.TokenManager.configureJwk
import ua.gleb.smartwaste.plugins.auth.TokenManager.issuer
import ua.gleb.smartwaste.plugins.auth.TokenManager.jwkProvider

const val AUTH_JWT = "auth_jwt"

fun Application.configureAuthorization() {
    configureJwk()
    install(Authentication) {
        jwt(AUTH_JWT) {
            realm = TokenManager.myRealm ?: ""
            jwkProvider?.let { provider ->
                verifier(provider, issuer ?: "") { acceptLeeway(3) }
            }
            validate { credential ->
                if (credential.payload.getClaim("id").asString() != "") {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
            challenge { _, _ ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
            }
        }
    }
}