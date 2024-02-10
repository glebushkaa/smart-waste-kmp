package ua.gleb.smartwaste.database.quest.table

import org.jetbrains.exposed.sql.Table

object QuestsTable : Table("quest") {

    val id = long("id").autoIncrement()
    val name = varchar("name", 128)
    val requiredProgress = integer("required_progress")

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}