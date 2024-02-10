package ua.gleb.smartwaste.network.user.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

@Serializable
data class NetworkUserResponse(
    @SerialName("id") val id: String? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("username") val username: String? = null,
    @SerialName("level") val level: Int? = null,
    @SerialName("progress") val progress: Int? = null,
    @SerialName("requiredProgress") val requiredProgress: Int? = null,
    @SerialName("days") val days: Int? = null,
    @SerialName("buckets") val buckets: Int? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("code") val code: String? = null,
)
