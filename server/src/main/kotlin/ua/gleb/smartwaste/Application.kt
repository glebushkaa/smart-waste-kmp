package ua.gleb.smartwaste

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.context.startKoin
import ua.gleb.smartwaste.core.BASE_URL
import ua.gleb.smartwaste.core.SERVER_PORT
import ua.gleb.smartwaste.database.SmartWasteDatabase
import ua.gleb.smartwaste.di.databaseModule
import ua.gleb.smartwaste.di.repositoryModule
import ua.gleb.smartwaste.plugins.auth.configureAuthorization
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
    SmartWasteDatabase.init()
    configureKoin()
    configureAuthorization()
    configureSerialization()
    configureRouting()
}

private fun configureKoin() {
    startKoin {
        modules(listOf(databaseModule, repositoryModule))
    }
}
