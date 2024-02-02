package ua.gleb.smartwaste.data.mapper

import ua.gleb.smartwaste.core.mapToImmutable
import ua.gleb.smartwaste.database.api.entity.RubbishEntity
import ua.gleb.smartwaste.model.Rubbish
import ua.gleb.smartwaste.network.api.items.model.NetworkRubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

fun ua.gleb.smartwaste.network.api.items.model.NetworkRubbish.toRubbish(): Rubbish {
    return Rubbish(
        name = name ?: "",
        categories = categories.mapToImmutable {
            it.toCategory()
        },
        id = id ?: 0,
    )
}

fun ua.gleb.smartwaste.network.api.items.model.NetworkRubbish.NetworkCategory.toCategory(): Rubbish.Category {
    return Rubbish.Category(
        name = name ?: "",
        slug = slug ?: "",
        id = id ?: 0,
        icon = icon ?: "",
    )
}

fun Rubbish.toRubbishEntity(): RubbishEntity {
    return RubbishEntity(
        id = this.id,
        name = this.name,
        count = this.count
    )
}

fun RubbishEntity.toRubbish(): Rubbish {
    return Rubbish(
        id = this.id,
        name = this.name,
        count = this.count
    )
}
