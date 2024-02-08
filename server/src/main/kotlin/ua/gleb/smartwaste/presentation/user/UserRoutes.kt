package ua.gleb.smartwaste.presentation.user

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ua.gleb.smartwaste.core.extensions.getIdFromToken
import ua.gleb.smartwaste.data.mapper.toNetworkQuestDto
import ua.gleb.smartwaste.data.mapper.toNetworkUserDto
import ua.gleb.smartwaste.data.network.request.PostQuestProgressRequest
import ua.gleb.smartwaste.data.network.response.ErrorResponse
import ua.gleb.smartwaste.domain.repository.QuestRepository
import ua.gleb.smartwaste.domain.repository.UserRepository
import ua.gleb.smartwaste.network.Routes
import ua.gleb.smartwaste.network.UserRoutes
import ua.gleb.smartwaste.network.user.dto.NetworkQuestsListDto
import ua.gleb.smartwaste.plugins.auth.AUTH_JWT

fun Routing.userRoute() {
    val userRepository by inject<UserRepository>()
    val questRepository by inject<QuestRepository>()

    route("/${Routes.USER.route}/") {
        getUserQuests(userRepository)
        getUser(userRepository)
        updateQuestProgress(questRepository)
    }
}


private fun Route.updateQuestProgress(questRepository: QuestRepository) {
    authenticate(AUTH_JWT) {
        post(UserRoutes.NEW_QUEST.route) {
            val id = call.getIdFromToken()
            val body = call.receive<PostQuestProgressRequest>()
            questRepository.updateUserQuestProgress(body.questId, id, body.progress)
            call.respond(HttpStatusCode.OK)
        }
    }
}

private fun Route.getUserQuests(userRepository: UserRepository) {
    authenticate(AUTH_JWT) {
        get(UserRoutes.QUESTS.route) {
            val id = call.getIdFromToken()
            val quests = userRepository.getUserQuests(id)
                .getOrDefault(emptyList())
                .map { quest -> quest.toNetworkQuestDto() }
            val questsDto = NetworkQuestsListDto(quests)
            call.respond(HttpStatusCode.OK, questsDto)
        }
    }
}

private fun Route.getUser(userRepository: UserRepository) {
    authenticate(AUTH_JWT) {
        get(UserRoutes.ME.route) {
            val id = call.getIdFromToken()
            val user = userRepository.getUser(id).getOrElse { throwable ->
                val response = ErrorResponse("user not found")
                call.respond(HttpStatusCode.NotFound, response)
                throw throwable
            }.toNetworkUserDto()
            call.respond(HttpStatusCode.OK, user)
        }
    }
}