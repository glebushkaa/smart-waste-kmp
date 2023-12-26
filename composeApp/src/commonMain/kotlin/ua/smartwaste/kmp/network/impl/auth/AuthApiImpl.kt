package ua.smartwaste.kmp.network.impl.auth

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.path
import ua.smartwaste.kmp.network.api.auth.AuthApi
import ua.smartwaste.kmp.network.api.auth.model.AuthResponse
import ua.smartwaste.kmp.network.impl.auth.dto.AuthResponseDto
import ua.smartwaste.kmp.network.impl.auth.dto.LoginDto
import ua.smartwaste.kmp.network.impl.auth.dto.RegisterDto
import ua.smartwaste.kmp.network.impl.mapper.toAuthResponse

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

class AuthApiImpl(
    private val authHttpClient: HttpClient,
) : AuthApi {

    override suspend fun login(
        email: String,
        password: String,
    ): AuthResponse {
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
    ): AuthResponse {
        val registerDto = RegisterDto(
            username = username,
            email = email,
            password = password,
        )
        return authHttpClient.post {
            url {
                path("signup")
                setBody(registerDto)
            }
        }.call.body<AuthResponse>()
    }
}
