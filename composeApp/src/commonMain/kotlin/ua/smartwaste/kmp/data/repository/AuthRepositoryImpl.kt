package ua.smartwaste.kmp.data.repository

import ua.smartwaste.kmp.domain.repository.AuthRepository
import ua.smartwaste.kmp.network.api.auth.AuthApi
import ua.smartwaste.kmp.preferences.api.AuthPreferences

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

class AuthRepositoryImpl(
    private val authApi: AuthApi,
    private val authPreferences: AuthPreferences,
) : AuthRepository {

    override suspend fun login(email: String, password: String) {
        authApi.login(email, password)
    }

    override suspend fun register(username: String, email: String, password: String) {
        authApi.register(username, email, password)
    }
}
