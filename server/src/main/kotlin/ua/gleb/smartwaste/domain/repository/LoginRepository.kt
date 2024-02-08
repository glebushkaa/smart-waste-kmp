package ua.gleb.smartwaste.domain.repository

import ua.gleb.smartwaste.domain.result.LoginResult

interface LoginRepository {

    suspend fun login(email: String, password: String): LoginResult

    suspend fun register(name: String, email: String, password: String): LoginResult
}