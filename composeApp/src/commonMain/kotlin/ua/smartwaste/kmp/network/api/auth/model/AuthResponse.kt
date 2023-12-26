package ua.smartwaste.kmp.network.api.auth.model

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

data class AuthResponse(
    val accessToken: String? = null,
    val code: String? = null,
    val message: String? = null,
)
