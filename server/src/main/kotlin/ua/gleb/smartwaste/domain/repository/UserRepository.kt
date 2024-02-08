package ua.gleb.smartwaste.domain.repository

import ua.gleb.smartwaste.model.Quest
import ua.gleb.smartwaste.model.User

interface UserRepository {

    suspend fun getUser(id: String): Result<User>

    suspend fun getUserQuests(id: String): Result<List<Quest>>
}