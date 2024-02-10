package ua.gleb.smartwaste.network.rubbish.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteRubbishRequest(
    @SerialName("id") val id: Long
)