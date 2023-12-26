package ua.smartwaste.kmp.data.mapper

import ua.smartwaste.kmp.model.User
import ua.smartwaste.kmp.network.api.user.model.NetworkUser

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

fun NetworkUser.toUser(days: Int): User {
    return User(
        id = id ?: "",
        email = email ?: "",
        username = username ?: "",
        score = score ?: 0,
        buckets = buckets ?: 0,
        days = days,
    )
}
