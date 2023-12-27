package ua.smartwaste.kmp.data.mapper

import kotlinx.collections.immutable.toImmutableList
import ua.smartwaste.kmp.model.Rubbish
import ua.smartwaste.kmp.network.api.items.model.NetworkRubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

fun NetworkRubbish.toRubbish(): Rubbish {
    return Rubbish(
        name = name ?: "",
        categories = categories.map {
            it.toCategory()
        }.toImmutableList(),
        id = id ?: 0,
    )
}

fun NetworkRubbish.NetworkCategory.toCategory(): Rubbish.Category {
    return Rubbish.Category(
        name = name ?: "",
        slug = slug ?: "",
        id = id ?: 0,
        icon = icon ?: "",
    )
}
