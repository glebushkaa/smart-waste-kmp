package ua.gleb.smartwaste.network.api.auth

import ua.gleb.smartwaste.network.api.auth.model.AuthResponse

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

interface AuthApi {

    suspend fun login(email: String, password: String): ua.gleb.smartwaste.network.api.auth.model.AuthResponse

    suspend fun register(username: String, email: String, password: String): ua.gleb.smartwaste.network.api.auth.model.AuthResponse
}
