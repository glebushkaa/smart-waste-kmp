package ua.gleb.smartwaste.database.rubbish.table

import org.jetbrains.exposed.dao.id.LongIdTable

object RubbishesTable : LongIdTable("rubbishes") {

    val title = varchar("title", 128).uniqueIndex()
    val emoji = varchar("emoji", 128)
}