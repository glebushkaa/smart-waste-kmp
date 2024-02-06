package ua.gleb.smartwaste.network.impl.user

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ua.gleb.smartwaste.network.UserRoutes
import ua.gleb.smartwaste.network.api.user.UserApi
import ua.gleb.smartwaste.network.api.user.model.NetworkQuest
import ua.gleb.smartwaste.network.api.user.model.NetworkUser
import ua.gleb.smartwaste.network.impl.mapper.toNetworkQuest
import ua.gleb.smartwaste.network.impl.mapper.toNetworkUser
import ua.gleb.smartwaste.network.user.dto.NetworkQuestsListDto
import ua.gleb.smartwaste.network.user.dto.NetworkUserDto

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

class UserApiImpl(
    private val userHttpClient: HttpClient,
) : UserApi {

    override suspend fun getUser(): NetworkUser {
        return userHttpClient.get(UserRoutes.ME.route)
            .call
            .body<NetworkUserDto>()
            .toNetworkUser()
    }

    override suspend fun getQuests(): List<NetworkQuest> {
        return userHttpClient.get(UserRoutes.QUESTS.route)
            .call
            .body<NetworkQuestsListDto>()
            .quests
            .map { it.toNetworkQuest() }
    }
}
