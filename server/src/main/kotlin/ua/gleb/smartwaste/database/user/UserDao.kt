package ua.gleb.smartwaste.database.user

import ua.gleb.smartwaste.database.user.entity.UserEntity
import java.util.*

interface UserDao {

    suspend fun userExists(email: String): Boolean

    suspend fun getAllUsers(): List<UserEntity>

    suspend fun getUser(id: UUID): UserEntity?

    suspend fun getPassword(email: String): String?

    suspend fun getIdByEmail(email: String): String?

    suspend fun register(name: String, email: String, password: String, createdAt: String): UserEntity?


    /**
     * Remove this function
     */
    suspend fun deleteAllUsers()
}
