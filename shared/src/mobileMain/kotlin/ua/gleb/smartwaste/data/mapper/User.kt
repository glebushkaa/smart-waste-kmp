package ua.gleb.smartwaste.data.mapper

import ua.gleb.smartwaste.model.User
import ua.gleb.smartwaste.network.api.user.model.NetworkUser

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

fun NetworkUser.toUser(
    days: Int,
    requiredProgress: Int,
): User {
    val score = score ?: 0
    return User(
        id = id ?: "",
        email = email ?: "",
        username = username ?: "",
        level = score / requiredProgress,
        currentProgress = score % requiredProgress,
        requiredProgress = requiredProgress,
        buckets = buckets ?: 0,
        days = days,
    )
}
