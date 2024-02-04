package ua.gleb.smartwaste.database.user

import ua.gleb.smartwaste.database.user.entity.UserEntity
import java.util.*

interface UserDao {

    suspend fun getAllUsers(): List<UserEntity>

    suspend fun getUser(id: UUID): UserEntity?
}
