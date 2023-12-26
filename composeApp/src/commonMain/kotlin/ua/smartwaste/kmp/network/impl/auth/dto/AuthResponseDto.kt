package ua.smartwaste.kmp.network.impl.auth.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

@Serializable
data class AuthResponseDto(
    @SerialName("accessToken") val accessToken: String? = null,
    @SerialName("code") val code: String? = null,
    @SerialName("message") val message: String? = null,
)
