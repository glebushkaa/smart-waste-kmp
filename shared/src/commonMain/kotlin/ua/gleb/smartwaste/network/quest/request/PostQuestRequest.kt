package ua.gleb.smartwaste.network.quest.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostQuestRequest(
    @SerialName("name") val name: String,
    @SerialName("requiredProgress") val requiredProgress: Int
)