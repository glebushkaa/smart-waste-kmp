package ua.gleb.smartwaste.database.user

import ua.gleb.smartwaste.database.user.entity.UserEntity
import java.util.*

interface UserDao {

    suspend fun userExists(email: String): Boolean

    suspend fun getAllUsers(): List<UserEntity>

    suspend fun getUser(id: UUID): UserEntity?

    suspend fun getUserByEmail(email: String): UserEntity?

    suspend fun register(name: String, email: String, password: String, createdAt: String): UserEntity?
}
