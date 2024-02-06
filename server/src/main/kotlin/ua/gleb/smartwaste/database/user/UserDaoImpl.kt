package ua.gleb.smartwaste.database.user

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import ua.gleb.smartwaste.data.mapper.toUserEntity
import ua.gleb.smartwaste.database.SmartWasteDatabase.dbQuery
import ua.gleb.smartwaste.database.user.entity.UserEntity
import ua.gleb.smartwaste.database.user.table.UserTable
import java.util.*

class UserDaoImpl : UserDao {

    override suspend fun userExists(email: String): Boolean = dbQuery {
        UserTable.select(UserTable.email)
            .where { UserTable.email eq email }
            .map { row -> row[UserTable.email] }
            .singleOrNull()
            ?.isNotBlank() ?: false
    }

    override suspend fun getAllUsers(): List<UserEntity> = dbQuery {
        UserTable.selectAll().map(::toUserEntity)
    }

    override suspend fun getUser(id: UUID): UserEntity? = dbQuery {
        UserTable.selectAll()
            .where { UserTable.id eq id }
            .map(::toUserEntity)
            .singleOrNull()
    }


    override suspend fun getPassword(email: String): String? = dbQuery {
        return@dbQuery UserTable.select(UserTable.password)
            .where(UserTable.email eq email)
            .map { it[UserTable.password] }
            .singleOrNull()
    }

    override suspend fun getIdByEmail(email: String): String? = dbQuery {
        return@dbQuery UserTable.select(UserTable.id)
            .where(UserTable.email eq email)
            .map { it[UserTable.id].toString() }
            .singleOrNull()
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        createdAt: String
    ): UserEntity? = dbQuery {
        return@dbQuery UserTable.insert { table ->
            table[this.name] = name
            table[this.email] = email
            table[this.password] = password
            table[progress] = 0
            table[requiredProgress] = 500
            table[this.createdAt] = createdAt
            table[buckets] = 0
        }.resultedValues?.map(::toUserEntity)?.singleOrNull()
    }

    override suspend fun deleteAllUsers() = dbQuery {
        UserTable.deleteAll()
        return@dbQuery
    }
}