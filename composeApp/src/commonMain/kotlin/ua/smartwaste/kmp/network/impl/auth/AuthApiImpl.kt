package ua.smartwaste.kmp.network.impl.auth

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import ua.smartwaste.kmp.network.api.auth.AuthApi
import ua.smartwaste.kmp.network.impl.auth.dto.AuthResponse
import ua.smartwaste.kmp.network.impl.auth.dto.LoginDto

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

class AuthApiImpl(
    private val authHttpClient: HttpClient,
) : AuthApi {

    override suspend fun login(email: String, password: String) {
        val loginDto = LoginDto(email = email, password = password)
        return authHttpClient.post {
            url {
                contentType(ContentType.Application.Json)
                path("signin")
                setBody(loginDto)
            }
        }.call.body<AuthResponse>()
    }

    override suspend fun register(username: String, email: String, password: String) {
    }

    override suspend fun deleteAccount() {
    }
}
