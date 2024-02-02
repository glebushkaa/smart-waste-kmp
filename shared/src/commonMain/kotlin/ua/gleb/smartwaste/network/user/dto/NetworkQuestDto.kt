package ua.gleb.smartwaste.network.user.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

@Serializable
data class NetworkQuestsListDto(
    @SerialName("quests") val quests: List<NetworkQuestDto> = emptyList(),
)

@Serializable
data class NetworkQuestDto(
    @SerialName("id") val id: Long? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("total") val total: Int = 0,
    @SerialName("completed") val completed: Int = 0,
)
