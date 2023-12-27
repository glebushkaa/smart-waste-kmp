package ua.smartwaste.kmp.network.impl.user

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ua.smartwaste.kmp.network.api.user.UserApi
import ua.smartwaste.kmp.network.api.user.model.NetworkQuest
import ua.smartwaste.kmp.network.api.user.model.NetworkUser
import ua.smartwaste.kmp.network.impl.mapper.toNetworkQuest
import ua.smartwaste.kmp.network.impl.mapper.toUser
import ua.smartwaste.kmp.network.impl.user.dto.NetworkQuestsListDto
import ua.smartwaste.kmp.network.impl.user.dto.NetworkUserDto

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

class UserApiImpl(
    private val userHttpClient: HttpClient,
) : UserApi {

    override suspend fun getUser(): NetworkUser {
        return userHttpClient.get("")
            .call
            .body<NetworkUserDto>()
            .toUser()
    }

    override suspend fun getQuests(): List<NetworkQuest> {
        return userHttpClient.get("quests")
            .call
            .body<NetworkQuestsListDto>()
            .quests
            .map { it.toNetworkQuest() }
    }
}
