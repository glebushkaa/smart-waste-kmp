package ua.gleb.smartwaste.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ua.gleb.smartwaste.routes.loginRoute
import ua.gleb.smartwaste.routes.userRoute

fun Application.configureRouting() {
    routing {
        userRoute()
        loginRoute()
        get {
            call.respondText { "Hello world!" }
        }
        staticResources("/.well-known", "files")
    }
}