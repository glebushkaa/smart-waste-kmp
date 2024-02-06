package ua.gleb.smartwaste.data.repository.user

import ua.gleb.smartwaste.model.User

interface UserRepository {

    suspend fun getUser(id: String): Result<User>

    suspend fun deleteAllUsers()
}