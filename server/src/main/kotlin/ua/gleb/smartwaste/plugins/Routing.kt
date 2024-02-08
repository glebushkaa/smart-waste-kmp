package ua.gleb.smartwaste.plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ua.gleb.smartwaste.presentation.login.loginRoute
import ua.gleb.smartwaste.presentation.quest.questRoute
import ua.gleb.smartwaste.presentation.user.userRoute

fun Application.configureRouting() {
    routing {
        questRoute()
        userRoute()
        loginRoute()
        get {
            call.respondText { "Hello world!" }
        }
        staticResources("/.well-known", "files")
    }
}