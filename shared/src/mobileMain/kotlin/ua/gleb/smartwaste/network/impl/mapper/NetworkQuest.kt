package ua.gleb.smartwaste.network.impl.mapper

import ua.gleb.smartwaste.network.api.user.model.NetworkQuest
import ua.gleb.smartwaste.network.user.dto.NetworkQuestDto

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

fun NetworkQuestDto.toNetworkQuest(): NetworkQuest {
    return NetworkQuest(
        id = id,
        name = name,
        total = total,
        completed = completed,
    )
}
