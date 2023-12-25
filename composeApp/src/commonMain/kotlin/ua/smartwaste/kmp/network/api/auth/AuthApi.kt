package ua.smartwaste.kmp.network.api.auth

import ua.smartwaste.kmp.network.api.auth.model.AuthResponse

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

interface AuthApi {

    suspend fun login(email: String, password: String): AuthResponse

    suspend fun register(username: String, email: String, password: String): AuthResponse
}
