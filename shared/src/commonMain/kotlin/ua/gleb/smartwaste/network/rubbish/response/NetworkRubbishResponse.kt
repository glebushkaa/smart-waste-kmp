package ua.gleb.smartwaste.network.rubbish.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

@Serializable
data class NetworkRubbishResponse(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String,
    @SerialName("emoji") val emoji: String,
)

@Serializable
data class NetworkRubbishListResponse(
    @SerialName("rubbishes") val rubbishes: List<NetworkRubbishResponse> = emptyList(),
)
