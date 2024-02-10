package ua.gleb.smartwaste.network.rubbish.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostRubbishRequest(
    @SerialName("title") val title: String,
    @SerialName("emoji") val emoji: String
)