package ua.gleb.smartwaste.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMessageBuilder
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ua.gleb.smartwaste.core.BASE_URL
import ua.gleb.smartwaste.core.SERVER_PORT
import ua.gleb.smartwaste.network.Routes
import ua.gleb.smartwaste.network.api.auth.AuthApi
import ua.gleb.smartwaste.network.api.items.ItemsApi
import ua.gleb.smartwaste.network.api.user.UserApi
import ua.gleb.smartwaste.network.impl.auth.AuthApiImpl
import ua.gleb.smartwaste.network.impl.items.ItemsApiImpl
import ua.gleb.smartwaste.network.impl.plugins.configureLogging
import ua.gleb.smartwaste.network.impl.plugins.configureSerialization
import ua.gleb.smartwaste.network.impl.user.UserApiImpl
import ua.gleb.smartwaste.preferences.api.AuthPreferences

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

private const val AUTH_HTTP_CLIENT = "AUTH_HTTP_CLIENT"
private const val USER_HTTP_CLIENT = "SELF_HTTP_CLIENT"
private const val ITEMS_HTTP_CLIENT = "ITEMS_HTTP_CLIENT"

val networkModule = module {
    singleAuthApi()
    singleUserApi()
    singleItemsApi()
}

private fun Module.singleItemsApi() {
    single<HttpClient>(named(ITEMS_HTTP_CLIENT)) {
        val authPreferences = get<AuthPreferences>()
        buildHttpClient {
            val token = "Bearer ${authPreferences.token ?: ""}"
            contentType(ContentType.Application.Json)
            header(HttpHeaders.Authorization, token)
        }
    }
    single<ItemsApi> {
        ItemsApiImpl(
            itemsHttpClient = get(named(ITEMS_HTTP_CLIENT)),
            fileUploader = get()
        )
    }
}

private fun Module.singleUserApi() {
    single<HttpClient>(named(USER_HTTP_CLIENT)) {
        val authPreferences = get<AuthPreferences>()
        buildHttpClient(additionalPath = Routes.USER.route) {
            val token = "Bearer ${authPreferences.token ?: ""}"
            contentType(ContentType.Application.Json)
            header(HttpHeaders.Authorization, token)
        }
    }

    single<UserApi> {
        UserApiImpl(userHttpClient = get(named(USER_HTTP_CLIENT)))
    }
}

private fun Module.singleAuthApi() {
    single<HttpClient>(named(AUTH_HTTP_CLIENT)) {
        buildHttpClient(additionalPath = Routes.AUTH.route) {
            contentType(ContentType.Application.Json)
        }
    }
    single<AuthApi> {
        AuthApiImpl(authHttpClient = get(named(AUTH_HTTP_CLIENT)))
    }
}

private fun buildHttpClient(
    baseUrl: String = BASE_URL,
    additionalPath: String? = null,
    httpMessageBuilder: HttpMessageBuilder.() -> Unit = {},
) = HttpClient {
    expectSuccess = true
    defaultRequest {
        url {
            protocol = URLProtocol.HTTP
            host = baseUrl
            port = SERVER_PORT
            additionalPath?.let { encodedPath = makeFullPath(it) }
        }
        httpMessageBuilder()
    }
    configureLogging()
    configureSerialization()
}

private fun URLBuilder.makeFullPath(path: String): String {
    val formattedBasePath = path.let {
        var formattedPath = it
        if (!path.startsWith("/")) {
            formattedPath = "/$path"
        }
        if (path.endsWith("/")) {
            formattedPath = path.dropLast(1)
        }
        formattedPath
    }
    return if (encodedPath.startsWith("/")) {
        formattedBasePath + encodedPath
    } else {
        "$formattedBasePath/$encodedPath"
    }
}
