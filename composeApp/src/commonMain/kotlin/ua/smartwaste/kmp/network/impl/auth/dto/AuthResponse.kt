package ua.smartwaste.kmp.network.impl.auth.dto

import kotlinx.serialization.SerialName

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

data class AuthResponse(
    @SerialName("accessToken") val accessToken: String? = null,
    @SerialName("code") val code: String? = null,
    @SerialName("message") val message: String? = null,
)
