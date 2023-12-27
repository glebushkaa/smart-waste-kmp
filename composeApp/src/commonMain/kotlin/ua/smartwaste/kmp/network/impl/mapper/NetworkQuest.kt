package ua.smartwaste.kmp.network.impl.mapper

import ua.smartwaste.kmp.network.api.user.model.NetworkQuest
import ua.smartwaste.kmp.network.impl.user.dto.NetworkQuestDto

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
