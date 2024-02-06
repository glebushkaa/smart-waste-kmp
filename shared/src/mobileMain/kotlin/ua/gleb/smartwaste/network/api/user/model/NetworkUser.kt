package ua.gleb.smartwaste.network.api.user.model

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

data class NetworkUser(
    val id: String? = null,
    val email: String? = null,
    val username: String? = null,
    val level: Int? = null,
    val progress: Int? = null,
    val requiredProgress: Int? = null,
    val days: Int? = null,
    val buckets: Int? = null,
    val message: String? = null,
    val code: String? = null,
)
