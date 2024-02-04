package ua.gleb.smartwaste.database.login

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import ua.gleb.smartwaste.data.mapper.toUserEntity
import ua.gleb.smartwaste.database.SmartWasteDatabase.dbQuery
import ua.gleb.smartwaste.database.user.entity.UserEntity
import ua.gleb.smartwaste.database.user.table.UserTable

class LoginDaoImpl : LoginDao {

    override suspend fun getPassword(email: String): String? = dbQuery {
        return@dbQuery UserTable.select(UserTable.password)
            .where(UserTable.email eq email)
            .map { it[UserTable.password] }
            .singleOrNull()
    }

    override suspend fun register(
        name: String, email: String, password: String, createdAt: String
    ): UserEntity? = dbQuery {
        return@dbQuery UserTable.insert { table ->
            table[this.name] = name
            table[this.email] = email
            table[this.password] = password
            table[level] = 0
            table[currentProgress] = 0
            table[requiredProgress] = 500
            table[this.createdAt] = createdAt
            table[buckets] = 0
        }.resultedValues?.map(::toUserEntity)?.singleOrNull()
    }
}