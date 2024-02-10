package ua.gleb.smartwaste.data.mapper

import ua.gleb.smartwaste.database.api.entity.RubbishEntity
import ua.gleb.smartwaste.model.Rubbish
import ua.gleb.smartwaste.network.api.items.model.NetworkRubbish

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/27/2023
 */

fun NetworkRubbish.toRubbish(): Rubbish {
    return Rubbish(
        id = this.id ?: 0,
        title = this.title ?: "",
        emoji = this.emoji ?: "",
        count = 0
    )
}

fun Rubbish.toRubbishEntity(): RubbishEntity {
    return RubbishEntity(
        id = this.id,
        name = this.title,
        count = this.count,
        emoji = this.emoji
    )
}

fun RubbishEntity.toRubbish(): Rubbish {
    return Rubbish(
        id = this.id,
        title = this.name,
        count = this.count,
        emoji = this.emoji
    )
}
