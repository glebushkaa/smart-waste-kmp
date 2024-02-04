package ua.gleb.smartwaste.data.repository.login

interface LoginRepository {

    suspend fun login(email: String, password: String): LoginResult

    suspend fun register(name: String, email: String, password: String): LoginResult
}