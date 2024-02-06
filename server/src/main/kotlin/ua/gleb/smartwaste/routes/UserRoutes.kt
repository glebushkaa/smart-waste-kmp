package ua.gleb.smartwaste.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ua.gleb.smartwaste.data.mapper.toNetworkUserDto
import ua.gleb.smartwaste.data.repository.user.UserRepository
import ua.gleb.smartwaste.network.Routes
import ua.gleb.smartwaste.network.UserRoutes
import ua.gleb.smartwaste.plugins.auth.AUTH_JWT

fun Routing.userRoute() {
    val userRepository by inject<UserRepository>()
    route("/${Routes.USER.route}/") {
        deleteAllUsersRoute(userRepository)
        getUserQuests(userRepository)
        getUser(userRepository)
    }
}

private fun Route.getUserQuests(userRepository: UserRepository) {
    authenticate(AUTH_JWT) {
        get(UserRoutes.QUESTS.route) {


        }
    }
}

private fun Route.getUser(userRepository: UserRepository) {
    authenticate(AUTH_JWT) {
        get(UserRoutes.ME.route) {
            val principal = call.principal<JWTPrincipal>()
            val id = principal?.payload?.getClaim("id")?.asString() ?: run {
                throw Throwable("id from token is null")
            }
            val user = userRepository.getUser(id).getOrElse { throwable ->
                call.respond(HttpStatusCode.NotFound, hashMapOf("message" to "user not found"))
                throw throwable
            }.toNetworkUserDto()
            call.respond(HttpStatusCode.OK, user)
        }
    }
}

/**
 * TODO Remove
 */
private fun Route.deleteAllUsersRoute(
    userRepository: UserRepository
) {
    delete("deleteAllUsers") {
        userRepository.deleteAllUsers()
        call.respondText(text = "removed", status = HttpStatusCode.OK)
    }
}