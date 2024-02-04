package ua.gleb.smartwaste.database.user.table

import org.jetbrains.exposed.sql.Table

object UserTable : Table("user") {
    val id = uuid("id").autoGenerate()
    val name = varchar("name", 128)
    val email = varchar("email", 128)
    val password = varchar("password", 256)
    val level = integer("level")
    val currentProgress = integer("current_progress")
    val requiredProgress = integer("required_progress")
    val createdAt = varchar("createdAt", 128)
    val buckets = integer("buckets")

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}