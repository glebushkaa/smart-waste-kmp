package ua.gleb.smartwaste.database.quest.table

import org.jetbrains.exposed.sql.Table

object UserQuestsTable : Table("user_quest") {

    val id = long("id").autoIncrement()
    val questId = long("quest_id").references(QuestsTable.id)
    val userId = uuid("user_id")
    val progress = integer("progress").nullable()

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}