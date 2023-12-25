package ua.smartwaste.kmp.network.api.auth

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

interface AuthApi {

    suspend fun login(email: String, password: String)

    suspend fun register(username: String, email: String, password: String)

    suspend fun deleteAccount()
}
