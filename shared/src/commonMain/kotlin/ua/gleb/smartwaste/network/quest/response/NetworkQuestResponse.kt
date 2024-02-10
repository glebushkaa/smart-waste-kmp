package ua.gleb.smartwaste.network.quest.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

@Serializable
data class NetworkQuestsListResponse(
    @SerialName("quests") val quests: List<NetworkQuestResponse> = emptyList(),
)

@Serializable
data class NetworkQuestResponse(
    @SerialName("id") val id: Long? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("total") val total: Int = 0,
    @SerialName("completed") val completed: Int = 0,
)
