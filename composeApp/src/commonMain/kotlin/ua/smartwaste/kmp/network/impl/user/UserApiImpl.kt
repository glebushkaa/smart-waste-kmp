package ua.smartwaste.kmp.network.impl.user

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ua.smartwaste.kmp.network.api.user.UserApi
import ua.smartwaste.kmp.network.api.user.model.NetworkUser
import ua.smartwaste.kmp.network.api.user.model.Quest
import ua.smartwaste.kmp.network.impl.mapper.toUser
import ua.smartwaste.kmp.network.impl.user.dto.NetworkUserDto

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

class UserApiImpl(
    private val userHttpClient: HttpClient,
) : UserApi {

    override suspend fun getUser(): NetworkUser {
        return userHttpClient.get("self")
            .call
            .body<NetworkUserDto>()
            .toUser()
    }

    override suspend fun getQuests(): List<Quest> {
        return emptyList()
    }
}
