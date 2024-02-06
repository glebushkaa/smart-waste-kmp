package ua.gleb.smartwaste.network.impl.mapper

import ua.gleb.smartwaste.network.api.user.model.NetworkUser
import ua.gleb.smartwaste.network.user.dto.NetworkUserDto

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/26/2023
 */

fun NetworkUserDto.toNetworkUser() = NetworkUser(
    id = id,
    username = username,
    email = email,
    progress = progress,
    requiredProgress = requiredProgress,
    level = level,
    buckets = buckets,
    days = days,
    message = message,
    code = code,
)
