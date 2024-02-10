package ua.gleb.smartwaste.data.mapper

import ua.gleb.smartwaste.database.rubbish.entity.RubbishEntity
import ua.gleb.smartwaste.network.rubbish.response.NetworkRubbishResponse

fun RubbishEntity.toNetworkRubbish() = NetworkRubbishResponse(
    id = id.value,
    title = title,
    emoji = emoji
)