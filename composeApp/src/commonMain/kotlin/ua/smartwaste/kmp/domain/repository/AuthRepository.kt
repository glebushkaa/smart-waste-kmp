package ua.smartwaste.kmp.domain.repository

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

interface AuthRepository {

    suspend fun login(email: String, password: String)

    suspend fun register(username: String, email: String, password: String)
}
