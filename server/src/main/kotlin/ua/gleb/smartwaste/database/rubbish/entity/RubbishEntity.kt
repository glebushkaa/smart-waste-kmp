package ua.gleb.smartwaste.database.rubbish.entity

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ua.gleb.smartwaste.database.rubbish.table.RubbishesTable

class RubbishEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<RubbishEntity>(RubbishesTable)

    var title by RubbishesTable.title
    var emoji by RubbishesTable.emoji
}