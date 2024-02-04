package ua.gleb.smartwaste.data.mapper

import ua.gleb.smartwaste.model.Quest
import ua.gleb.smartwaste.network.api.user.model.NetworkQuest

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

fun NetworkQuest.toQuest(): Quest {
    return Quest(
        id = id ?: 0,
        title = name ?: "",
        requiredProgress = total,
        currentProgress = completed,
    )
}
