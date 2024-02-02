package ua.gleb.smartwaste.network.impl.plugins

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging

fun HttpClientConfig<*>.configureLogging() {
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
}