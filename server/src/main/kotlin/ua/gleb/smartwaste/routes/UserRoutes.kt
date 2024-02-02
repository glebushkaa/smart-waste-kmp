package ua.gleb.smartwaste.routes

import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ua.gleb.smartwaste.network.user.dto.NetworkUserDto

fun Routing.userRoute() {
    route("/self/") {
        get {
            val dto = NetworkUserDto(
                id = "1",
                email = "gleb.mokryy@gmail.com",
                username = "gle.bushkaa",
                score = 1200,
                createdAt = "2023-11-17T15:20:02.759Z",
                buckets = 10
            )
            val json = Json.encodeToString(dto)
            call.respondText(
                text = json,
                status = HttpStatusCode.OK,
                contentType = ContentType.Application.Json
            )
        }
        get("quests") {

        }
    }
}