package ua.smartwaste.kmp.network.api.user.model

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/25/2023
 */

data class NetworkQuest(
    val id: Long? = null,
    val name: String? = null,
    val total: Int = 0,
    val completed: Int = 0,
)
