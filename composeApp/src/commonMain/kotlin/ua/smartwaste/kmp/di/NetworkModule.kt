package ua.smartwaste.kmp.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMessageBuilder
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ua.smartwaste.kmp.network.api.auth.AuthApi
import ua.smartwaste.kmp.network.api.user.UserApi
import ua.smartwaste.kmp.network.impl.auth.AuthApiImpl
import ua.smartwaste.kmp.network.impl.user.UserApiImpl
import ua.smartwaste.kmp.preferences.api.AuthPreferences

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

private const val AUTH_HTTP_CLIENT = "AUTH_HTTP_CLIENT"
private const val USER_HTTP_CLIENT = "SELF_HTTP_CLIENT"

private const val BASE_URL = "smartwaste-api.azurewebsites.net"

val networkModule = module {
    single<HttpClient>(named(AUTH_HTTP_CLIENT)) {
        buildHttpClient(baseUrl = "$BASE_URL/auth")
    }
    single<AuthApi> {
        AuthApiImpl(authHttpClient = get(named(AUTH_HTTP_CLIENT)))
    }

    single<HttpClient>(named(USER_HTTP_CLIENT)) {
        val authPreferences = get<AuthPreferences>()
        buildHttpClient(baseUrl = "$BASE_URL/self") {
            val token = authPreferences.token ?: ""
            contentType(ContentType.Application.Json)
            header(HttpHeaders.Authorization, token)
        }
    }

    single<UserApi> {
        UserApiImpl(userHttpClient = get(named(USER_HTTP_CLIENT)))
    }
}

private fun buildHttpClient(
    baseUrl: String = BASE_URL,
    httpMessageBuilder: HttpMessageBuilder.() -> Unit = {},
): HttpClient {
    return HttpClient {
        defaultRequest {
            host = baseUrl
            url { protocol = URLProtocol.HTTPS }
            httpMessageBuilder()
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                },
            )
        }
    }
}
