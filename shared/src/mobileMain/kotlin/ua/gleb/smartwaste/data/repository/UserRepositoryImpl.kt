package ua.gleb.smartwaste.data.repository

import kotlinx.collections.immutable.ImmutableList
import ua.gleb.smartwaste.core.mapToImmutable
import ua.gleb.smartwaste.data.mapper.toQuest
import ua.gleb.smartwaste.data.mapper.toUser
import ua.gleb.smartwaste.domain.exception.AuthException
import ua.gleb.smartwaste.model.Quest
import ua.gleb.smartwaste.model.User
import ua.gleb.smartwaste.network.api.user.UserApi

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

class UserRepositoryImpl(
    private val userApi: UserApi,
) : ua.gleb.smartwaste.domain.repository.UserRepository {

    override suspend fun getQuests(): ImmutableList<Quest> {
        return userApi.getQuests().mapToImmutable { it.toQuest() }
    }

    override suspend fun getUser(): User {
        val response = userApi.getUser()
        if (response.message?.isNotEmpty() == true) {
            val exception = AuthException(
                code = GET_USER_EXCEPTION,
                message = response.message,
            )
            throw exception
        }
        val days = 0
        return response.toUser(
            days = days,
            requiredProgress = REQUIRED_LEVEL_PROGRESS,
        )
    }


    private companion object {
        private const val GET_USER_EXCEPTION = 300
        private const val REQUIRED_LEVEL_PROGRESS = 500
    }
}
