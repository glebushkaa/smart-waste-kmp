package ua.gleb.smartwaste.presentation.rubbish

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import ua.gleb.smartwaste.network.rubbish.request.DeleteRubbishRequest
import ua.gleb.smartwaste.network.rubbish.request.PostRubbishRequest
import ua.gleb.smartwaste.domain.repository.RubbishRepository
import ua.gleb.smartwaste.network.Routes
import ua.gleb.smartwaste.network.RubbishRoutes
import ua.gleb.smartwaste.network.rubbish.response.NetworkRubbishListResponse
import ua.gleb.smartwaste.plugins.auth.AUTH_JWT

fun Route.rubbishRoutes() {
    val rubbishRepository by inject<RubbishRepository>()

    route("/${Routes.RUBBISH.route}/") {
        getAllRubbish(rubbishRepository)
        postNewRubbish(rubbishRepository)
        deleteRubbish(rubbishRepository)
    }
}

private fun Route.getAllRubbish(rubbishRepository: RubbishRepository) {
    authenticate(AUTH_JWT) {
        get(RubbishRoutes.ALL.route) {
            val rubbishes = rubbishRepository.getAllRubbishes()
            val list = NetworkRubbishListResponse(rubbishes)
            call.respond(HttpStatusCode.OK, list)
        }
    }
}

private fun Route.postNewRubbish(rubbishRepository: RubbishRepository) {
    authenticate(AUTH_JWT) {
        post(RubbishRoutes.NEW.route) {
            val request = call.receive<PostRubbishRequest>()
            rubbishRepository.addNewRubbish(
                title = request.title,
                emoji = request.emoji
            )
            call.respond(HttpStatusCode.OK)
        }
    }
}

private fun Route.deleteRubbish(rubbishRepository: RubbishRepository) {
    authenticate(AUTH_JWT) {
        delete {
            val request = call.receive<DeleteRubbishRequest>()
            rubbishRepository.deleteRubbish(id = request.id)
            call.respond(HttpStatusCode.OK)
        }
    }
}