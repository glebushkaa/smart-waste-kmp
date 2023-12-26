package ua.smartwaste.kmp.network.impl.user

import io.ktor.client.HttpClient
import ua.smartwaste.kmp.network.api.user.UserApi
import ua.smartwaste.kmp.network.api.user.model.Quest

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

class UserApiImpl(
    private val userHttpClient: HttpClient,
) : UserApi {

    override suspend fun getQuests(): List<Quest> {
        return emptyList()
    }
}
