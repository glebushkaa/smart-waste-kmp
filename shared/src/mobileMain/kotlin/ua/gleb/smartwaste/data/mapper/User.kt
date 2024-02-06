package ua.gleb.smartwaste.data.mapper

import ua.gleb.smartwaste.model.User
import ua.gleb.smartwaste.network.api.user.model.NetworkUser

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

fun NetworkUser.toUser(): User {
    return User(
        id = id ?: "",
        email = email ?: "",
        username = username ?: "",
        level = level ?: 0,
        currentProgress = progress ?: 0,
        requiredProgress = requiredProgress ?: 500,
        buckets = buckets ?: 0,
        days = days ?: 0,
    )
}
