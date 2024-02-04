package ua.gleb.smartwaste.database.login

import ua.gleb.smartwaste.database.user.entity.UserEntity

interface LoginDao {

    suspend fun getPassword(email: String): String?

    suspend fun register(name: String, email: String, password: String, createdAt: String): UserEntity?
}