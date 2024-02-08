package ua.gleb.smartwaste.data.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostQuestProgressRequest(
    @SerialName("quest_id") val questId: Long,
    @SerialName("progress") val progress: Int
)