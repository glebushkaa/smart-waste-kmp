package ua.gleb.smartwaste.network.auth.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

@Serializable
data class RegisterDto(
    @SerialName("name") val username: String,
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
)
