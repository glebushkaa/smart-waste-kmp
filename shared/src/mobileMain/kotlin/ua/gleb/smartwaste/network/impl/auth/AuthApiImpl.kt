package ua.gleb.smartwaste.network.impl.auth

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.path
import ua.gleb.smartwaste.network.auth.dto.AuthResponseDto
import ua.gleb.smartwaste.network.auth.dto.LoginDto
import ua.gleb.smartwaste.network.auth.dto.RegisterDto
import ua.gleb.smartwaste.network.impl.mapper.toAuthResponse

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

class AuthApiImpl(
    private val authHttpClient: HttpClient,
) : ua.gleb.smartwaste.network.api.auth.AuthApi {

    override suspend fun login(
        email: String,
        password: String,
    ): ua.gleb.smartwaste.network.api.auth.model.AuthResponse {
        val loginDto = LoginDto(email = email, password = password)
        return authHttpClient.post {
            url {
                path("signin")
                setBody(loginDto)
            }
        }.call.body<AuthResponseDto>().toAuthResponse()
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String,
    ): ua.gleb.smartwaste.network.api.auth.model.AuthResponse {
        val registerDto = RegisterDto(
            username = username,
            email = email,
            password = password
        )
        return authHttpClient.post {
            url {
                path("signup")
                setBody(registerDto)
            }
        }.call.body<AuthResponseDto>().toAuthResponse()
    }
}
