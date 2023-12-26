package ua.smartwaste.kmp.network.impl.mapper

import ua.smartwaste.kmp.network.api.user.model.NetworkUser
import ua.smartwaste.kmp.network.impl.user.dto.NetworkUserDto

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

fun NetworkUserDto.toUser() = NetworkUser(
    id = id,
    username = username,
    email = email,
    score = score,
    buckets = buckets,
    createdAt = createdAt,
    message = message,
    code = code,
)
