package ua.smartwaste.kmp.network.impl.items.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

@Serializable
data class NetworkRubbishListDto(
    @SerialName("items") val items: List<NetworkRubbishDto> = emptyList(),
)

@Serializable
data class NetworkRubbishDto(
    @SerialName("id") val id: Long? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("categories") val categories: List<NetworkCategoryDto> = emptyList(),
) {
    @Serializable
    data class NetworkCategoryDto(
        @SerialName("id") val id: Long? = null,
        @SerialName("name") val name: String? = null,
        @SerialName("slug") val slug: String? = null,
        @SerialName("emoji") val icon: String? = null,
    )
}
