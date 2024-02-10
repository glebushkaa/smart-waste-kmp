package ua.gleb.smartwaste.network.impl.mapper

import ua.gleb.smartwaste.network.api.items.model.NetworkRubbish
import ua.gleb.smartwaste.network.rubbish.response.NetworkRubbishResponse
import ua.gleb.smartwaste.network.rubbish.response.NetworkRubbishListResponse

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */


fun NetworkRubbishListResponse.toNetworkRubbishList(): List<NetworkRubbish> {
    return rubbishes.map { it.toNetworkRubbish() }
}

fun NetworkRubbishResponse.toNetworkRubbish(): NetworkRubbish {
    return NetworkRubbish(
        id = this.id,
        title = this.title,
        emoji = this.emoji
    )
}