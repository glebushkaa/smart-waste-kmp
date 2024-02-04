package ua.gleb.smartwaste.database.user.entity

data class UserEntity(
    val id: String? = null,
    val username: String? = null,
    val email: String? = null,
    val level: Int? = null,
    val currentProgress: Int? = null,
    val requiredProgress: Int? = null,
    val createdAt: String? = null,
    val buckets: Int? = null,
)