package ua.gleb.smartwaste.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import ua.gleb.smartwaste.routes.userRoute

fun Application.configureRouting() {
    routing {
        userRoute()
        get {
            call.respondText { "Hello world!" }
        }
    }
}