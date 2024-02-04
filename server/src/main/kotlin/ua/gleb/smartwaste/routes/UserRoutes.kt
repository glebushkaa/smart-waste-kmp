package ua.gleb.smartwaste.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.ktor.ext.inject
import ua.gleb.smartwaste.data.mapper.toNetworkUser
import ua.gleb.smartwaste.data.repository.user.UserRepository
import ua.gleb.smartwaste.network.user.dto.NetworkQuestDto
import ua.gleb.smartwaste.network.user.dto.NetworkQuestsListDto

fun Routing.userRoute() {
    val userRepository by inject<UserRepository>()
    route("/self/") {
        get("users") {
            val users = userRepository.getAllUsers().map { user ->
                user.toNetworkUser()
            }
            val json = Json.encodeToString(users)
            call.respondText(
                text = json,
                status = HttpStatusCode.OK,
                contentType = ContentType.Application.Json
            )
        }

        get("quests") {
            val quest = NetworkQuestDto(
                id = 1,
                name = "Collect garbage",
                total = 2,
                completed = 3
            )
            val listDto = NetworkQuestsListDto(
                quests = listOf(quest)
            )
            val json = Json.encodeToString(listDto)
            call.respondText(
                text = json,
                status = HttpStatusCode.OK,
                contentType = ContentType.Application.Json
            )
        }
    }
}