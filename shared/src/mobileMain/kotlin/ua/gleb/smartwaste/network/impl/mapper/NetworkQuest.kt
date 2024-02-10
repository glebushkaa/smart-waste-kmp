package ua.gleb.smartwaste.network.impl.mapper

import ua.gleb.smartwaste.network.api.user.model.NetworkQuest
import ua.gleb.smartwaste.network.quest.response.NetworkQuestResponse

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

fun NetworkQuestResponse.toNetworkQuest(): NetworkQuest {
    return NetworkQuest(
        id = id,
        name = name,
        total = total,
        completed = completed,
    )
}
