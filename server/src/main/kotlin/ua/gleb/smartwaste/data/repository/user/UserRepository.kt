package ua.gleb.smartwaste.data.repository.user

import ua.gleb.smartwaste.model.User

interface UserRepository {

    suspend fun getAllUsers(): List<User>

    suspend fun getUser(id: String): User?
}