package ua.gleb.smartwaste.database.impl.mapper

import smartwaste.rubbish.Rubbish
import ua.gleb.smartwaste.database.api.entity.RubbishEntity

/**
 * Created by gle.bushkaa email(gleb.mokryy@gmail.com) on 12/31/2023
 */

fun Rubbish.toRubbishEntity(): RubbishEntity {
    return RubbishEntity(
        id = this.id,
        name = this.name,
        count = this.count.toInt()
    )
}