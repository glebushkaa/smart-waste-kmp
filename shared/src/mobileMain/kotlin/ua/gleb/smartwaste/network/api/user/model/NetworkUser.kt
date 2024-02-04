package ua.gleb.smartwaste.network.api.user.model

import kotlinx.serialization.SerialName

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

data class NetworkUser(
    @SerialName("id") val id: String? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("username") val username: String? = null,
    @SerialName("score") val score: Int? = null,
    @SerialName("createdAt") val createdAt: String? = null,
    @SerialName("buckets") val buckets: Int? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("code") val code: String? = null,
)
