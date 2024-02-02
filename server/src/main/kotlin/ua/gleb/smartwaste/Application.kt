package ua.gleb.smartwaste

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import ua.gleb.smartwaste.core.BASE_URL
import ua.gleb.smartwaste.core.SERVER_PORT
import ua.gleb.smartwaste.plugins.configureRouting
import ua.gleb.smartwaste.plugins.configureSerialization


fun main() {
    embeddedServer(
        Netty,
        port = SERVER_PORT,
        host = BASE_URL,
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
}
