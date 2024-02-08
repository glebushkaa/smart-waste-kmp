package ua.gleb.smartwaste.presentation.quest

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ua.gleb.smartwaste.data.mapper.toNetworkQuestDto
import ua.gleb.smartwaste.data.network.request.PostQuestRequest
import ua.gleb.smartwaste.domain.repository.QuestRepository
import ua.gleb.smartwaste.network.QuestRoutes
import ua.gleb.smartwaste.network.Routes
import ua.gleb.smartwaste.network.user.dto.NetworkQuestsListDto
import ua.gleb.smartwaste.plugins.auth.AUTH_JWT

fun Routing.questRoute() {
    val questRepository by inject<QuestRepository>()
    route("/${Routes.QUEST.route}/") {
        postNewQuest(questRepository)
        getAllQuests(questRepository)
    }
}

private fun Route.postNewQuest(questRepository: QuestRepository) {
    authenticate(AUTH_JWT) {
        post {
            val request = call.receive<PostQuestRequest>()
            questRepository.addNewQuest(
                name = request.name,
                requiredProgress = request.requiredProgress
            )
            call.respond(HttpStatusCode.OK)
        }
    }
}

private fun Route.getAllQuests(questRepository: QuestRepository) {
    authenticate(AUTH_JWT) {
        get(QuestRoutes.ALL.route) {
            val quests = questRepository.getAllQuests().map {
                it.toNetworkQuestDto()
            }
            val questsDto = NetworkQuestsListDto(quests)
            call.respond(HttpStatusCode.OK, questsDto)
        }
    }
}