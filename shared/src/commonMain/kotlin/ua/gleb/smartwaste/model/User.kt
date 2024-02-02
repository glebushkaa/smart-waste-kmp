package ua.gleb.smartwaste.model

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

data class User(
    val id: String,
    val username: String,
    val email: String,
    val level: Int,
    val currentProgress: Int,
    val requiredProgress: Int,
    val days: Int = 0,
    val buckets: Int = 0,
)

fun emptyUser(): User = User(
    id = "",
    username = "",
    email = "",
    level = 0,
    currentProgress = 0,
    requiredProgress = 0,
)
