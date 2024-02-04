package ua.gleb.smartwaste.network.api.items.model

import kotlinx.collections.immutable.persistentListOf

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

data class NetworkRubbish(
    val id: Long? = null,
    val name: String? = null,
    val count: Int? = null,
    val limit: Int? = null,
    val categories: List<ua.gleb.smartwaste.network.api.items.model.NetworkRubbish.NetworkCategory> = persistentListOf(),
) {
    data class NetworkCategory(
        val id: Long? = null,
        val name: String? = null,
        val slug: String? = null,
        val icon: String? = null,
    )
}
