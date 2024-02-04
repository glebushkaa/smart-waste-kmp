package ua.gleb.smartwaste.network.impl.mapper

import ua.gleb.smartwaste.network.api.items.model.NetworkRubbish
import ua.gleb.smartwaste.network.items.dto.NetworkRubbishDto

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

fun NetworkRubbishDto.toNetworkRubbish(): NetworkRubbish {
    return NetworkRubbish(
        id = id,
        name = name,
        categories = categories.map {
            it.toNetworkCategory()
        },
    )
}

fun NetworkRubbishDto.NetworkCategoryDto.toNetworkCategory(): NetworkRubbish.NetworkCategory {
    return NetworkRubbish.NetworkCategory(
        id = id,
        name = name,
        slug = slug,
        icon = icon,
    )
}
